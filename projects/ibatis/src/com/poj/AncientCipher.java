package com.poj;

/**
 * 密文还原
 * 
 * 原始字符串为: VICTORIOUS
 * 先将字符串中的每个字符改为其下一个字符，如果为Z，则改为A
 * 原文改为：WJDUPSJPVT
 * 然后根据序列{2,1,5,4,3,7,6,10,9,8}将字符串重新排列
 * 最终为:JWPUDJSTVP
 * 
 * @author QJF
 *
 */
public class AncientCipher {

	public static void main(String[] args) {
		String cipher = "JWPUDJSTVP";
		int[] orderArr = {2,1,5,4,3,7,6,10,9,8};
		
		char[] oriArr = new char[cipher.length()];
		char[] cipherArr = cipher.toCharArray();
		for(int i = 0; i < oriArr.length; i++) {
			int order = orderArr[i];
			if(cipherArr[order-1] == 'Z') {
				oriArr[i] = 'A';
			} else {
				oriArr[i] = (char)(cipherArr[order-1] - 1);
			}
		}
		
		System.out.println(String.valueOf(oriArr));
	}
}
