package com.sa.structure.list;

public class TestList {

	private static void testLinearTable1() {
		LinearTable1 lt1 = new LinearTable1();
		
		lt1.add(0,"1");
		lt1.add(1,"2");
		lt1.add(2,"3");
		lt1.add(3,"4");
		lt1.add(4,"5");
		lt1.add("6");
		lt1.add("7");
		lt1.add("8");
		lt1.add("9");
		lt1.add("10");
		lt1.add("11");
		lt1.add("12");
		
		System.out.println(lt1.size());
		System.out.println(lt1.isEmpty());
		System.out.println(lt1);
		System.out.println(lt1.get(4));
		System.out.println(lt1.remove(14));
		System.out.println(lt1);
	}
	
	private static void testLinearTable2() {
		LinearTable2<String> lt2 = new LinearTable2<String>();
		lt2.add("1");
		lt2.add("2");
		lt2.add("3");
		lt2.add("4");
		lt2.add("5");
		lt2.add("6");
		lt2.add("7");
		
		System.out.println(lt2.toString());
		lt2.remove(3);
		System.out.println(lt2.toString());
		lt2.insert(4, "8");
		System.out.println(lt2.toString());
	}
	
	private static void testLinearTable3() {
		LinearTable3<String> lt3 = new LinearTable3<String>();
		lt3.addBeforeHead("5");
		lt3.addBeforeHead("4");
		lt3.addBeforeHead("3");
		lt3.addBeforeHead("2");
		lt3.addBeforeHead("1");
		
//		lt3.addAfterHead("1.5");
		
		lt3.insert(4, "8");
		
		lt3.remove(5);
		System.out.println(lt3);
	}
	
	private static void testLinearTable4() {
		LinearTable4<String> lt4 = new LinearTable4<String>();
		
		
		lt4.add("3");
		lt4.add("4");
		lt4.add("5");
		lt4.add("6");
		lt4.add("7");
		lt4.add("8");
		lt4.add("9");
		
		lt4.insert(1, "1");
		lt4.insert(2, "2");
		
		System.out.println(lt4);
		
		lt4.remove(9);
		
		System.out.println(lt4);
	}
	
	private static void testLinearTable5() {
		LinearTable5 lt = new LinearTable5();
		lt.add("1");
		lt.add("2");
		lt.add("3");
		lt.add("4");
		lt.add("5");
		lt.add("6");
		lt.add("7");
		lt.add("8");
		lt.add("9");
		lt.add("10");
		lt.add("6");
		System.out.println(lt.size());
		lt.remove();
		lt.remove();
		System.out.println(lt.size());
		
		System.out.println(lt);
	}
}
