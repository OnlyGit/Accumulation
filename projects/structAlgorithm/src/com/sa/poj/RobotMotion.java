package com.sa.poj;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个表格由方向组成的表格，从第一行任意位置进入，求走出表格用多少步，
 * 如果永远走不出表格，重复循环多少步。
 * 
 * 如：
 * 		neeswe
 * 		wwwess
 * 		snwwww
 * 
 * 如果当前位置是n，表示下一步想北(上)移，依次类推
 * 
 * 如何记录重复步骤？
 * 记录位置，因为每一个位置一定只对应一个方向，如果在步骤记录中第一次出现与前面
 * 相同的位置，那么这个一定是重复步骤的第一步。
 * @author QJF
 *
 */
public class RobotMotion {

	public static void main(String[] args) {
		//从第一行的第5列进入表格
		char[][] track = buildGrid(new String[] {"neeswe","wwwess","snwwww"});
		getResult(track,4);
		
		//从第一行的第1列进入表格
		track = buildGrid(new String[] {"seswe","eesnw","nween","ewsen"});
		getResult(track,0);
	}
	
	private static char[][] buildGrid(String[] column) {
		char[][] track = new char[column.length][column[1].length()];
		for(int i = 0; i < track.length; i++) {
			track[i] = column[i].toCharArray();
		}
		return track;
	}

	private static void getResult(char[][] track, int entranceColumn) {
		int rowLength = track.length;
		int columnLength = track[0].length;
		
		for(int i = 0; i < track.length; i++) {
			for(int j = 0; j < track[i].length; j++) {
				System.out.print(track[i][j]);
			}
			System.out.println();
		}
		
		boolean flag = true;//是否为无限循环
		int columnPos = entranceColumn;
		int rowPos = 0;
		
		char next = track[rowPos][entranceColumn];
		String position = "";
		
		List<String> steps = new ArrayList<String>();
		
		while(flag && (columnPos >= 0 && columnPos < columnLength && rowPos >= 0 && rowPos < rowLength)) {
			next = track[rowPos][columnPos];
			if(next == 'n') {
				rowPos--;
			} else if(next == 's') {
				rowPos++;
			} else if(next == 'w') {
				columnPos--;
			} else if(next == 'e') {
				columnPos++;
			} else {
				
			}
			position = ""+rowPos+columnPos;
			if(steps.contains(position)) {
				System.out.println(position);
				flag = false;
			}
			steps.add(position);
		}
		
		if(!flag) {//无限循环
			System.out.println("无限循环"+(steps.size()-1-steps.indexOf(position))+"步");
		} else {
			System.out.println(steps.size()+"步后退出");
		}
	}
}
