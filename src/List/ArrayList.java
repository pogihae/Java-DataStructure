package List;
import java.util.Arrays;


/**
 * 
 * This class implements a Array List that use Dynamic array
 * 
 * @author tir29
 *
 * @param <E>
 */
public class ArrayList<E> {
	
	private static final int DEFAULT_SIZE = 10;
	private E[] list;
	private int size;
	
	
	/**
	 * Constructor
	 * 
	 * @param length the starting array
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList(int size) {
		list = (E[])(new Object[size]);
		this.size = 0;
	}
	
	/*NO-args constructor*/
	public ArrayList() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * add element to list
	 * if max, increase capacity of array twice
	 * 
	 * @param to insert element
	 */
	public void add(E elem) {
		if(list.length < size+1) {
			grow();
		}

		list[size++] = elem;
	}
	
	/*doubling capacity*/
	private void grow() {
		list = Arrays.copyOf(list,size*2);
	}
	
	/**
	 * check element is in list
	 * 
	 * @param checked element
	 * @return true, if exist
	 */
	public boolean isExist(E elem) {
		return getIdx(elem) > 0;
	}
	
	/**
	 * get index of given element
	 * 
	 * @param checked element
	 * @return index, if non exist -1
	 */
	private int getIdx(E elem) {
		for(int i=0; i<size; i++) {
			if(elem.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * remove given element
	 * polling elements by removed index
	 * 
	 * @param removed element
	 * @return false if non exist
	 */
	public boolean remove(E elem) {
		int rmvIdx = getIdx(elem);
		
		if(rmvIdx < 0) {
			return false;
		}
		//polling
		System.arraycopy(list, rmvIdx+1, list, rmvIdx, (size-1)-rmvIdx);
		
		list[--size] = null;
		
		return true;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		Object[] toStr = new Object[size];
		System.arraycopy(list, 0, toStr, 0, size);
		return Arrays.toString(toStr);
	}
	
}
