package com.sa.structure.list;

public class TestTestStructure {

	public static void main(String[] args) {
		testLinearTable2();
	}
	
	
	private static void testLinearTable1() {
		LinearTableTest1 lt1 = new LinearTableTest1();
		
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
		System.out.println(lt1);
		System.out.println(lt1.get(4));
		lt1.remove(4);
		System.out.println(lt1);
	}
	
	private static void testLinearTable2() {
		LinearTableTest2<String> lt2 = new LinearTableTest2<String>();
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
}