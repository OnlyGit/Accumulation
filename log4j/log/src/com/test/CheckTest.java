package com.test;

import java.util.regex.Pattern;

public class CheckTest {
	public static void main(String[] args) {
//		String s = "192.168.1.2~192.168.1.34;192.168.3.2;192.168.3.5~192.168.3.50";
//		String s = "192.168.3.2;192.168.1.2~192.168.1.34;192.168.3.5~192.168.3.50";
//		String s = "192.0.0.2;192.168.1.2~192.168.1.34;192.168.3.2;192.168.3.5~192.168.3.254;";
//		String s = "192.168.1.2~192.168.1.34;";
		String s = "‎08-60-6E-DA-C0-43~‎08-60-6F-DA-C0-43;‎08-61-6E-DA-C0-43";
//		String s = "‎08-61-6E-DA-C0-43";
//		String oneIpcheck = "((([1-9])|([1-9]\\d)|((1\\d{2})|(2([0-4]\\d)|2(5[0-4]))))\\.)((([\\d])|([1-9]\\d)|((1\\d{2})|(2([0-4]\\d)|2(5[0-4]))))\\.){2}((([1-9])|([1-9]\\d)|((1\\d{2})|(2([0-4]\\d)|2(5[0-4])))))";
//		String oneIpcheck = "([A-F\\d]{2}-){5}[A-F\\d]{2}";
		String oneIpcheck = "((([A-F]|\\d){2}-){5}([A-F]|\\d){2})";
		
		String checkAll = "(("+oneIpcheck+"~"+oneIpcheck+")|("+oneIpcheck+"))"+"(;(("+oneIpcheck+")|("+oneIpcheck+"~"+oneIpcheck+")))*[;]?";
		System.out.println(checkAll);
//		System.out.println(Pattern.compile(checkAll).matcher("08-60-6E-DA-C0-43;08-61-6E-DA-D0-43~08-61-6E-DA-C0-43;08-60-6E-DA-C0-43;").matches());
		System.out.println(Pattern.compile(checkAll).matcher("08-60-6E-DA-C0-43~‎08-60-6F-DA-C0-43;‎08-61-6E-DA-C0-43;").matches());
	}

}
