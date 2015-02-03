package lucene.joe.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class JoeIndex {
	
	private void indexDocs(IndexWriter writer, File file) throws IOException {
		if (file.canRead())
			if (file.isDirectory()) {
				String[] files = file.list();

				if (files != null)
					for (int i = 0; i < files.length; i++)
						indexDocs(writer, new File(file, files[i]));
			} else {
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException fnfe) {
					return;
				}

				try {
					Document doc = new Document();

					Field pathField = new StringField("path", file.getPath(),Field.Store.YES);
					doc.add(pathField);

					doc.add(new LongField("modified", file.lastModified(),Field.Store.NO));
					
					doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))));

					if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
						System.out.println("adding " + file);
						writer.addDocument(doc);
					} else {
						System.out.println("updating " + file);
						writer.updateDocument(new Term("path", file.getPath()),doc);
					}
				} finally {
					fis.close();
				}
			}
	}

	/**
	 * 索引操作
	 * @param docsPath 文档目录
	 * @param indexPath 索引生成目录
	 */
	private void operateIndex(String docsPath, String indexPath, OpenMode mode) {
		try {
			File docDir = new File(docsPath);
			if ((!docDir.exists()) || (!docDir.canRead())) {
				System.out.println("Document directory '"+ docDir.getAbsolutePath()
								+ "' does not exist or is not readable, please check the path");
				return;
			}
			
			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
			iwc.setOpenMode(mode);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			indexDocs(writer, docDir);
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成索引
	 * @param indexPath
	 */
	public void generateIndex(String docsPath, String indexPath) {
		this.operateIndex(docsPath, indexPath, OpenMode.CREATE);
	}
	
	/**
	 * 更新索引
	 * @param docsPath
	 * @param indexPath
	 */
	public void updateIndex(String docsPath, String indexPath) {
		this.operateIndex(docsPath, indexPath, OpenMode.CREATE_OR_APPEND);
	}
	
	public void deleteIndex(String indexPath) {
		try {
			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			//此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
			writer.deleteDocuments(new Term("title", "nginx.txt"));
//			writer.deleteAll();//删除所有索引，永久删除
			
			writer.commit();
			writer.close();
			this.getAllIndex(indexPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllIndex() {
		
	}
	
	public void mergerIndex(String indexPath) {
		try {
			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			writer.forceMergeDeletes();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看所有索引
	 * @param index
	 */
	public void getAllIndex(String index) {
		try {
			IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(index)));
			IndexSearcher searcher = new IndexSearcher(reader);
			Document doc = null;
			for(int i = 0; i < reader.maxDoc(); i++) {
				doc = searcher.doc(i);
				System.out.println(doc.get("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
