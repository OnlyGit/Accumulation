package lucene.joe.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class JoeSearch {

	public static void main(String[] args) {
		String index = "index";
		String field = "contents";
		String content = "服务";
		search(index, field, content);
	}

	private static void search(String index, String field, String content) {
		IndexReader reader;
		try {
			reader = DirectoryReader.open(FSDirectory.open(new File(index)));
			IndexSearcher searcher = new IndexSearcher(reader);
			
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
			QueryParser parser = new QueryParser(Version.LUCENE_4_10_3, field,analyzer);
			Query query = parser.parse(content);
			
			TopDocs results = searcher.search(query, 5);
			
			ScoreDoc[] hits = results.scoreDocs;

			int numTotalHits = results.totalHits;
			System.out.println(numTotalHits + " total matching documents");
			
			for(int i = 0; i < hits.length; i ++) {
				Document doc = searcher.doc(hits[i].doc);
				String path = doc.get("path");
				System.out.println(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
