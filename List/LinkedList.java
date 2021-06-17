/*
 * This class implements Linked list
 * 
 * @param element type <E>
 */
public class LinkedList<E> {
	
	/*Node class that saving elements*/
	private static class Node<E> {
		E data;
		Node<E> next;
		
		Node() {
			this(null, null);
		}
		
		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
		
		@Override
		public boolean equals(Object o) {
			return data.equals(o);
		}
	}
	
	/*list's head, always indicate dummy elem*/
	private Node<E> head;
	
	/*used for searching*/
	private Node<E> cur;
	private Node<E> prev;
	
	private int size;
	
	/*
	 * constructor
	 * 
	 * make node for head that includes dump data
	 * for easier coding
	 */
	public LinkedList() {
		head = new Node<>(); //dummy
		size = 0;
	}
	
	/*
	 * add element to list
	 * most recetly add element locations
	 * head next
	 * 
	 * @param inserted element
	 */
	public void add(E e) {
		head.next = new Node<>(e,head.next);
		size++;
	}
	
	/*
	 * checking given element is in list
	 * set cur, prev for remove
	 * 
	 * @param for checking element
	 * @return true or false
	 */
	public boolean isExist(E e) {
		cur = head.next;
		prev = head;
		
		while(cur != null) {
			if(cur.equals(e)) {
				return true;
			}
			
			prev = cur;
			cur = cur.next;
		}
		
		return false;
	}
	
	/*
	 * remove element
	 * set prev, cur by isExist
	 * 
	 * @param deleted data
	 * @return true or false
	 */
	public boolean remove(E e) {
		if(!isExist(e)) {
			return false;
		}
		
		prev.next = cur.next;
		size--;
		
		return true;
	}
	
	/*
	 * todo: use iterator
	 * */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node<E> tmp = head.next;
		
		while(tmp != null) {
			sb.append(tmp.data.toString()+", ");
			tmp = tmp.next;
		}
		
		String str = sb.toString();
		str = str.substring(0,str.length()-2);
		return str;
	}
	
	
	/*
	 * todo : use list interface
	 * */
	@Override
	public boolean equals(Object o) {
		
		cur = head.next;
		while(cur != null) {
			if(!cur.equals(o)) {
				return false;
			}
		}
		return true;
	}
}
