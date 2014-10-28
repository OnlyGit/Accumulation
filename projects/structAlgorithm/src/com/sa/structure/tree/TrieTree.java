package com.sa.structure.tree;

/**
 * 字典树
 * 不区分大小写
 * @author QJF
 */
public class TrieTree {
	
	private TrieNode root;//一个trieTree只有一个根节点

	public TrieTree() {
		root = new TrieNode();
	}
	/**
	 * 新增单词
	 * @param word
	 */
	public void inserWord(String word) {
		if(null == word || "".equals(word)) {
			return;
		}
		TrieNode cur = this.root;
		TrieNode node = null;
		
		word = word.toLowerCase();
		int index = 0;
		char[] cs = word.toCharArray();
		for(int i = 0; i < cs.length; i++) {
			if(cs[i] >= 'a' && cs[i] <= 'z') {
				index = cs[i] - 'a';//获取位置
			} else {
				return;
			}
			
			node = cur.getChildren()[index];
			
			if(node == null) {
				TrieNode newnode = new TrieNode();
				newnode.setValue(cs[i]);
				if(i == cs.length - 1) {
					newnode.setWord(true);
				}
				cur.getChildren()[index] = newnode;
				cur = newnode;
			} else {
				if(i == cs.length - 1) {
					node.setWord(true);
				}
				cur = node;
			}
		}
	}
	/**
	 * 查找单词
	 * @param word
	 * @return
	 */
	public boolean searchWord(String word) {
		if(null == word || "".equals(word)) {
			return false;
		}
		
		TrieNode cur = this.root;
		TrieNode node = null;
		
		word = word.toLowerCase();
		int index = 0;
		char[] cs = word.toCharArray();
		for(int i = 0; i < cs.length; i++) {
			if(cs[i] >= 'a' && cs[i] <= 'z') {
				index = cs[i] - 'a';//获取位置
			} else {
				return false;
			}
			
			node = cur.getChildren()[index];
			if(node == null) {
				return false;
			}
			cur = node;
		}
		
		return true;
	}
	/**
	 * 打印单词
	 * @param node
	 * @param word
	 * @param index
	 */
	public void printTree(TrieNode node, char[] word, int index) {
		if(node == null) {
			return;
		}
		if(node.isWord()) {//当前字符是否是一个单词的结尾
			for(char c : word) {
				if(c != ' ' && (int)c != 0) {
					System.out.print(c);
				}
			}
			System.out.println();
		}
		TrieNode[] children = node.getChildren();
		for(TrieNode cur : children) {
			if(cur != null) {
				word[index++] = cur.getValue();
				printTree(cur, word, index);
				word[index--] = ' ';
			}
		}
	}
	
	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		
		tree.inserWord("you");
		tree.inserWord("and");
		tree.inserWord("we");
		tree.inserWord("a");
		System.out.println(tree.searchWord("we"));
		
		char[] word = new char[26];
		tree.printTree(tree.root, word, 0);
	}
}

class TrieNode {//节点类
	private static final int NUMBER = 26;
	private char value;
	private boolean isWord;
	protected TrieNode[] children = new TrieNode[NUMBER];
	
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public boolean isWord() {
		return isWord;
	}
	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}
	public TrieNode[] getChildren() {
		return children;
	}
	public void setChildren(TrieNode[] children) {
		this.children = children;
	}
}