package lucene.joe.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * lucene与IKAnalyzer结合使用
 * @Author QJF
 * @Desception 
 * @Date 2015年1月29日下午2:57:34
 */
public class CombineDemo {

	public static void main(String[] args) {
		try {
			search("免费的开放源代码的");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void search(String keyWord) throws Exception {
		String index = "index";
		String field = "contents";
		
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		
		Analyzer analyzer = new IKAnalyzer(true);
		
		QueryParser parser = new QueryParser(Version.LUCENE_4_10_3, field,analyzer);
		
		Query query = parser.parse(keyWord);
		System.out.println("Searching for: " + query.toString(field));
		TopDocs topDocs = searcher.search(query, 5);
		System.out.println("匹配文件个数：" + topDocs.totalHits);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;  
        for (int i = 0; i < topDocs.totalHits; i++) {  
            Document targetDoc = searcher.doc(scoreDocs[i].doc);  
            System.out.println(targetDoc.get("path")); 
        }
	}
}
