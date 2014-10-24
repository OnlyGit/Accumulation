package com.sa.structure.list;

//线性表-----基于数组的循环队列
/**
 * front = rear 表示队列为空
 * rear的下一个位置如果为front，表示队列已满
 */
public class LinearTable5 {

	private Object[] array;
	private int front;//第一个元素的位置
	private int rear;//最后一个元素的下一个位置
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
			System.out.println("队列已满！");
			return;
		}
		array[rear] = val;
		rear = (rear + 1) % array.length;
	}
	
	public void remove() {
		if(front == rear % array.length) {
			System.out.println("队列为空！");
		}
		System.out.println(array[front] + "----出队！");
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
