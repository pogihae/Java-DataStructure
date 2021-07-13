package sort;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
	private static final int DEFAULT_SIZE = 10;
	private static final int ROOT = 1;
	
	private T[] elements;
	private int size;
	private int capacity;
	private final boolean maxOrMin; // true: maxHeap | false: minHeap
	
	/**
	 * @param capacity, initialize
	 * @param maxOrMin, Max heap: true, Min heap: false 
	 */
	@SuppressWarnings("unchecked")
	public Heap(int capacity, boolean maxOrMin) {
		this.elements = (T[])(new Comparable[capacity]);
		this.size = 0;
		this.capacity = capacity;
		this.maxOrMin = maxOrMin;
	}
	
	public Heap() {
		this(DEFAULT_SIZE, false);
	}
	
	/**
	 * @param dest, to compared data
	 * @param sorc, to move data
	 * @return 1: sorc is more near root than dest
	 */
	private int compare(T dest, T sorc) {
		if(maxOrMin) return sorc.compareTo(dest);
		else return dest.compareTo(sorc);
	}
	
	/**
	 * add in heap, complete binary tree
	 * 
	 * @param to insert
	 */
	public void add(T t) {
		if(++size >= capacity) {
			grow();
		}
		
		int i = size / 2;
		int j = size;
		while(i>0) {
			if(compare(elements[i], t) <= 0) {
				break;
			}
			
			elements[j] = elements[i];
			j = i;
			i /= 2;
		}
		elements[j] = t;
	}
	
	/**
	 * for array capacity increase
	 */
	void grow() {
		elements = Arrays.copyOf(elements, ++capacity);
	}
	
	/**
	 * @return Root elemenet, that priority highest elem
	 */
	public T pull() {
		if(size == 0) {
			return null;
		}
		T pulled = elements[ROOT];
		T moved = elements[size];
		
		int i = ROOT;
		int j = getMaxChildIdx(i);
		while(true) {
			if(j == 0 || compare(elements[j], moved) >= 0) {
				break;
			}
			
			elements[i] = elements[j];
			i = j;
			j = getMaxChildIdx(i);
		}
		
		elements[i] = moved;
		size--;
		
		return pulled;
	}
	
	int getMaxChildIdx(int parentIdx) {
		if(parentIdx*2 > size) return 0;
		else if(parentIdx*2+1 > size) return parentIdx*2;
		else return (compare(elements[parentIdx*2], elements[parentIdx*2+1]) < 0)?
				parentIdx*2 : parentIdx*2+1;
	}
	
	public int size() {
		return size;
	}
}
