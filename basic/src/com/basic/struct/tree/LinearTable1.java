package com.basic.struct.tree;

//���Ա�----˳��洢
public class LinearTable1 {

	private final int DEFAULT_SIZE = 10;//�����Ĭ�ϳ���
	private int size;// ��ǰ�����С(Ԫ�ظ���)
	private Object[] arr;
	
	public LinearTable1() {
		this.init(DEFAULT_SIZE);
	}
	
	public LinearTable1(int size) {
		this.init(size);
	}
	
	private void init(int size) {
		this.size = 0;
		this.arr = new Object[size];
	}
	
	public void add(int index, Object val) {
		this.checkSize(index);
		
		if(size == this.arr.length) {
			Object[] newArr = new Object[this.arr.length * 3 / 2 + 1];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			this.arr = newArr;
		}

		for (int j = size; j > index; j--) {
			arr[j] = arr[j - 1]; // ��Ҫ����λ�ú������Ԫ�������ƶ�һλ
		}
		arr[index] = val;
		size ++;
	}
	
	//Ĭ�ϼ������һ��λ�õĺ���
	public void add(Object val) {
		this.add(this.size, val);
	}
	
	public Object remove(int index) {
		this.checkSize(index);
		if(size == 0) {
			throw new RuntimeException("�������Ѿ�û��Ԫ�أ�");
		}
		Object result = arr[index];
		for(int i = index; i < this.size; i++) {
			arr[i] = arr[i + 1];
		}
		this.size--;
		return result;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < this.size; i++) {
			result.append(this.arr[i]+",");
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}
	
	public Object get(int index) {
		this.checkSize(index);
		return this.arr[index];
	}
	
	public boolean isEmpty() {
		return 0 == this.size;
	}
	//����Ԫ�ظ���
	public int size() {
		return this.size;
	}
	
	public void checkSize(int index) {
		if(index < 0 || index > this.arr.length) {
			throw new IndexOutOfBoundsException();
		}
	}
}
