package com.poj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 单词匹配：
 * 输出words中与targetWords可以匹配的单词。
 * 如何是达到匹配？
 * 在targetWords中新增一个字符、替换一个字符或者删除一个字符
 * @author QJF
 */
public class SpellChecker {
	
	private static Set<String> hashSet = new HashSet<String>();
	
	private static Map<String,Set<String>> map = new HashMap<String,Set<String>>();

	public static void main(String[] args) {
		String[] words = {"i","is","has","have","be","my","more","contest","me","too","if","award"};
		setDictionary(words);
		
		String[] targetWords = {"me","aware","m","contest","hav","oo","or","i","fi","mre"};
		
		for(int i = 0; i< targetWords.length; i++) {
			Set<String> set = new HashSet<String>();
			map.put(targetWords[i], set);
			if(hashSet.contains(targetWords[i])) {
				set.add(targetWords[i]);
				continue;
			}
			for(int j = 0; j < words.length; j++) {
				check(words[j],targetWords[i],set);
			}
		}
		
		for(String targetWord : targetWords) {
			System.out.print(targetWord+"--");
			Set<String> set = (Set<String>)map.get(targetWord);
			for(String s : set) {
				System.out.print(s+" ");
			}
			System.out.println();
		}
	}

	private static void check(String ori,String result,Set<String> set) {
		char[] oriArr = ori.toCharArray();
		char[] resultArr = result.toCharArray();
		
		if(ori.length() == result.length()) {
			//将result替换一个字符
			for(int i = 0; i < oriArr.length; i++) {
				replace(result, oriArr[i], i,set);
			}
		}
		if(ori.length() - result.length() == -1) {
			//将result删除一个字符
			for(int i = 0; i < resultArr.length; i++) {
				delete(result, i,set);
			}
		}
		if(ori.length() - result.length() == 1) {
			//在result插入一个字符
			for(int i = 0; i < oriArr.length; i++) {
				insert(result, oriArr[i], i,set);
			}
		}
	}
	
	/**
	 * 构造内存字典
	 * @param words
	 */
	private static void setDictionary(String[] words) {
		if(words != null && words.length > 0) {
			for(String word : words) {
				hashSet.add(word);
			}
		}
	}
	
	/**
	 * 替换一个字符
	 */
	private static void replace(String ori, char target, int index,Set<String> set) {
		StringBuffer sb = new StringBuffer(ori.substring(0,index));
		sb.append(target);
		sb.append(ori.substring(index+1));
		if(hashSet.contains(sb.toString())) {
			set.add(sb.toString());
		}
	}
	
	/**
	 * 删除一个字符
	 */
	private static void delete(String ori, int index,Set<String> set) {
		StringBuffer sb = new StringBuffer(ori.substring(0,index));
		sb.append(ori.substring(index+1));
		if(hashSet.contains(sb.toString())) {
			set.add(sb.toString());
		}
	}
	
	/**
	 * 插入一个字符
	 */
	private static String insert(String ori, char target, int index,Set<String> set) {
		StringBuffer sb = new StringBuffer();
		char[] oriArr = ori.toCharArray();
		if(index == oriArr.length) {
			if(hashSet.contains(ori+target)) {
				set.add(ori+target);
			}
			return ori+target;
		}
		for(int i = 0; i < oriArr.length; i++) {
			if(i == index) {
				sb.append(target);
			}
			sb.append(oriArr[i]);
		}
		if(hashSet.contains(sb.toString())) {
			set.add(sb.toString());
		}
		return sb.toString();
	}
}
