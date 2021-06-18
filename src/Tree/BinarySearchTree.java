package Tree;

public class BinarySearchTree<E extends Comparable<E>> {
	
	private static class Node<E extends Comparable<E>> {
		E data;
		Node<E> left;
		Node<E> right;
		
		Node() {
			this(null);
		}
		
		Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	Node<E> root;
	
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(E root) {
		this.root = new Node<>(root);
	}
	
	public void add(E e) {
		Node<E> toAdd = new Node<>(e);
		
		Node<E> cur = setCur(toAdd);
		
		if(cur.data.compareTo(toAdd.data) < 0) {
			cur.right = toAdd;
		}
		else {
			cur.left = toAdd;
		}
	}
	
	Node<E> setCur(Node<E> toAdd){
		Node<E> tmp = root;
		Node<E> cur = tmp;
		
		while(tmp != null) {
			int cmp = tmp.data.compareTo(toAdd.data);
			cur = tmp;
			
			if(cmp == -1) {
				tmp = tmp.right;
			}
			else {
				tmp = tmp.left;
			}
		}
		
		return cur;
	}
	
	
}
