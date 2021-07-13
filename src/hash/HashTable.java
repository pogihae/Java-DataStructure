package hash;

import list.LinkedList;
import java.util.Arrays;
import java.util.function.ToIntFunction;


/**
 * 
 * table using hash key, dynamic array of list
 * 
 * @author tir29
 * @param <T>
 */
public class HashTable<T> {
	private LinkedList<T>[] keys;
	private ToIntFunction<T> hashFunc;
	
	public HashTable() {
		this(10,
		x -> (int)(x.toString().charAt(0))
		);
	}
	
	public HashTable(ToIntFunction<T> hashFunc) {
		this(10, hashFunc);
	}
	
	/**
	 * constructor
	 * 
	 * @param size, for initailize list array
	 * @param hashFunc, use lambda method
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size, ToIntFunction<T> hashFunc) {
		keys = (LinkedList<T>[])(new LinkedList[size]);
		
		this.hashFunc = hashFunc;
	}
	
	/**
	 * 
	 * insert data
	 * if data's hashkey is over than array capacity
	 * increase array until hashkey
	 * 
	 * @param data to insert
	 */
	public void insert(T data) {
		//exist keys
		int key = hashFunc.applyAsInt(data);
		
		//non exist
		if(!isExistKey(key)) {
			grow(key);
		}
		
		keys[key].add(data);
	}
	
	/**
	 * @param key that return hash function
	 * @return true and false, 
	 * at key in array has list and key is over bound or don't have list
	 */
	private boolean isExistKey(int key) {
		if(key >= keys.length) return false;
		else if(keys[key] != null) return true;
		else return false;
	}
	
	/**
	 * increase array bound with param
	 * 
	 * @param key
	 */
	private void grow(int key) {
		//array grow
		if(key >= keys.length) {
			keys = Arrays.copyOf(keys, key+1);
		}
		
		//list allocation
		keys[key] = new LinkedList<>();
		
	}
	
	/**
	 * @param data
	 * @return {@code true} if data is removed {@code ,}
	 * 			{@code false} otherwise
	 */
	public boolean remove(T data) {
		int key = hashFunc.applyAsInt(data);
		if(!isExistKey(key)) return false;
		return keys[key].remove(data);
	}
	
	//search
	public boolean isExist(T data) {
		int key = hashFunc.applyAsInt(data);
		if(!isExistKey(key)) return false;
		return keys[key].isExist(data);
	}
}
