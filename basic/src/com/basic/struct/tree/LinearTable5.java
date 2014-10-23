package com.basic.struct.tree;

//���Ա�-----���������ѭ������
/**
 * front = rear ��ʾ����Ϊ��
 * rear����һ��λ�����Ϊfront����ʾ��������
 */
public class LinearTable5 {

	private Object[] array;
	private int front;//��һ��Ԫ�ص�λ��
	private int rear;//���һ��Ԫ�ص���һ��λ��
	private int size;
	private static final int DEFAULT_SIZE = 5;
	
	public LinearTable5() {
		this.init(DEFAULT_SIZE);
	}
	
	public LinearTable5(int size) {
		this.init(size);
	}
	
	private void init(int size) {
		this.size = 0;
		front = rear = 0;
		array = new Object[size];
	}
	
	public void add(Object val) {
		if(front == (rear + 1) % array.length) {
			System.out.println("����������");
			return;
		}
		array[rear] = val;
		rear = (rear + 1) % array.length;
	}
	
	public void remove() {
		if(front == rear % array.length) {
			System.out.println("����Ϊ�գ�");
		}
		System.out.println(array[front] + "----���ӣ�");
		array[front] = null;
		front = (front + 1) % array.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return (rear - front + array.length) % array.length;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i = front; i < size() + front; i++) {
			result.append(this.array[i % array.length]+",");
		}
//		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}
}
