
public class LinkedList<E> {
	private class Node{
		E data;
		Node next;
		
		Node(){
			this(null, null);
		}
		
		Node(E data, Node next){
			this.data = data;
			this.next = next;
		}
		
		/*boolean equals(E e) {
			return data.equals(e);
		}*/
	}
	
	private Node head;
	
	private Node cur;
	private Node prev;
	
	private int size;
	
	public LinkedList() {
		head = new Node();//trash node to easy implement
		size = 0;
	}
	
	public void add(E e) {
		Node tmp = head;
		while(tmp.next != null) {
			tmp = tmp.next;
		}
		
		tmp.next = new Node(e,null);
		size++;
	}
	
	public boolean isExist(E e) {
		cur = head.next;
		prev = head;
		
		while(cur != null) {
			if(cur.data.equals(e)) {
				return true;
			}
			
			prev = cur;
			cur = cur.next;
		}
		
		return false;
	}
	
	public boolean remove(E e) {
		if(!isExist(e)) {
			return false;
		}
		
		prev.next = cur.next;
		size--;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node tmp = head.next;
		
		while(tmp != null) {
			sb.append(tmp.data.toString()+", ");
			tmp = tmp.next;
		}
		
		return sb.toString();
	}
}
