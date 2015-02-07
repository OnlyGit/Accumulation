package lucene.joe.client;

import lucene.joe.search.JoeIndex;

public class Client {

	public static void main(String[] args) {
		JoeIndex index = new JoeIndex();
		index.generateIndex("search", "index");
//		index.getAllIndex("index");
		
//		index.deleteIndex("index");
//		index.deleteAllIndex("index");
	}
}
