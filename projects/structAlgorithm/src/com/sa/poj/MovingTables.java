package com.sa.poj;

/**
 * 一条走廊的两侧分别有200个房间，如：
 * 
 * room1 room3 room5....room397 room399\
 * ------------------------------------
 * ------------------------------------
 * room2 room4 room6....room398 room400
 * 
 * 将一个房间中的桌子搬到另一个房间，而走廊的宽度只够一张桌子
 * 例如要将桌子从1搬到6，则1至6之间的走廊被占用，只能搬完后才可使用
 * 但是可以同时将桌子从7搬到10
 * 
 * 问题：搬一张桌子需要10min，求n张桌子搬完需要多久
 * 输入：
 * n
 * n1 n2
 * n3 n4
 * ....
 * 
 * 说明：n表示n张桌子，n1 n2表示从n1运到n2,n1 n2表示从n3运到n4
 * 
 * 
 * 
 * 解决思路：将起始房间与目的房间看作一个区间，找出所有区间重叠的最大值
 * @author QJF
 */
public class MovingTables {

	public static void main(String[] args) {
		test(3,new int[]{10,100,20,80,30,50});
	}

	private static void test(int tablesCount,int[] arr) {
		int[] opsitions = new int[400];
		
		for(int i = 0; i < tablesCount; i++) {
			int indexBegin = arr[2*i];
			int indexEnd = arr[2*i + 1];
			
			if(indexBegin % 2 == 0) {//indexBegin和indexBegin-1共用一部分走廊
				indexBegin--;
			}
			
			if(indexEnd % 2 != 0) {//indexEnd和indexEnd+1共用一部分走廊
				indexEnd++;
			}
			
			for(int j = indexBegin; j < indexEnd + 1; j++) {
				opsitions[j-1]++;
			}
		}
		
		System.out.println(getMax(opsitions));
	}
	
	private static int getMax(int[] arr) {
		int max = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		
		return max;
	}
}
