package lucene.joe.test;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerDemo {

	public static void main(String[] args) {
		Analyzer analyzer = new IKAnalyzer(true);

		// 获取Lucene的TokenStream对象
		TokenStream ts = null;
		String text = "请确定2月7日到京参加答疑会及讲座的尽快办理手续、自己预定会场附近酒店";
		try {
			ts = analyzer.tokenStream("demo", new StringReader(text));
			// 获取词元位置属性
			OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
			// 获取词元文本属性
			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			// 获取词元文本属性
			TypeAttribute type = ts.addAttribute(TypeAttribute.class);
			// 重置TokenStream（重置StringReader）
			ts.reset();
			// 迭代获取分词结果
			while (ts.incrementToken()) {
				System.out.println(offset.startOffset() + " - "
						+ offset.endOffset() + " : " + term.toString() + " | "
						+ type.type());
			}
			// 关闭TokenStream（关闭StringReader）
			ts.end(); // Perform end-of-stream operations, e.g. set the final
						// offset.

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放TokenStream的所有资源
			if (ts != null) {
				try {
					ts.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			ikSeg();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void ikSeg() throws Throwable {
		String content = "请确定2月7日到京参加答疑会及讲座的尽快办理手续、自己预定会场附近酒店";
		IKSegmenter ikSeg = new IKSegmenter(new StringReader(content),true);

		Lexeme l = null;
		while ((l = ikSeg.next()) != null) {
			String word = l.getLexemeText();
			int wordType = l.getLexemeType();
			System.out.println(wordType + "->" + word);
		}
	}
}
