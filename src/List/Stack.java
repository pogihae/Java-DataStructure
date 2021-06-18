package List;
import java.util.NoSuchElementException;

/**
 * @author tir29
 * @see test for commit
 *
 * @param <E> type
 */
public class Stack<E> {
	
	private static final int TOP = 0;
	
	private LinkedList<E> stack;
	
	/*
	 * constructor
	 * 
	 * make linkedlist
	 */
	public Stack() {
		stack = new LinkedList<>();
	}
	
	
	/**
	 * 
	 * add data to stack on top
	 * 
	 * @param data to push
	 */
	public void push(E e) {
		stack.add(e);
	}
	
	/**
	 * 
	 * get top and remove it
	 * 
	 * @return top of data
	 * @throws NoSuchElementException by peek
	 */
	public E pop() {
		E data = peek();
		stack.remove(data);
		
		return data;
	}
	
	/**
	 * 
	 * just peek top, no remove it
	 * 
	 * @return top of data
	 * @throws NoSuchElementException
	 */
	public E peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Empty Stack");
		}
		
		E data = stack.get(TOP);
		return data;
	}
	
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	/*
	 * todo: use iterator
	 * */
	@Override
	public String toString() {
		return stack.toString();
	}
}
