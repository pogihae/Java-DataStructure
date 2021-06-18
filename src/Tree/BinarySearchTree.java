package Tree;

public class BinarySearchTree<E extends Comparable<E>> {
	
	private static class Node<E extends Comparable<E>> {
		E data;
		Node<E> left;
		Node<E> right;
		
		Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	private Node<E> root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public BinarySearchTree(E root) {
		this.root = new Node<>(root);
	}
	
	public void add(E e) {
		Node<E> toAdd = new Node<>(e);
		
		Node<E> tmp = root;
		Node<E> prev = tmp;
		int cmp = -2;
		
		while(tmp != null) {
			prev = tmp;
			cmp = tmp.data.compareTo(toAdd.data);
			
			if(cmp < 0) {
				tmp = tmp.right;
			}
			else {
				tmp = tmp.left;
			}
		}
		
		if(cmp == -2) {
			root = toAdd;
		}
		else {
			if(cmp < 0) {
				prev.right = toAdd;
			}
			else {
				prev.left = toAdd;
			}
		}
	}
	
	public E remove(E e) {
		//set parent toDel
		Node<E> pvRoot = new Node<>(null);
		pvRoot.right = root;
		
		Node<E> toDel = root;
		Node<E> parent = pvRoot;
		boolean flag = false;
		
		while(toDel != null) {
			int cmp = toDel.data.compareTo(e);
			
			if(cmp < 0) {
				parent = toDel;
				toDel = toDel.right;
			}
			else if(cmp == 0) {
				flag = true;
				break;
			}
			else {
				parent = toDel;
				toDel = toDel.left;
			}
		}
		
		if(!flag) {
			return null;
		}
		E delData = toDel.data;
		//remove by childNum
		while(true) {
			int childNum = getChildNum(toDel);
			boolean isLeftChild = (toDel.data.compareTo(parent.data) <= 0)? true:false;
			
			switch(childNum) {
			case 0:
				if(isLeftChild) {
					parent.left = null;
				}
				else {
					parent.right = null;
				}
				break;
			case -1: case 1:
				if(isLeftChild) {
					parent.left = (childNum == 1)? toDel.left : toDel.right;
				}
				else {
					parent.right = (childNum == 1)? toDel.left : toDel.right;
				}
				break;
			case 2:
				toDel.data = getLeftBigest(toDel).data;
				toDel = getLeftBigest(toDel);
				continue;
			}
			break;
		}
		
		return delData;
	}
	
	private int getChildNum(Node<E> n) {
		if(n.right == null && n.left == null) {
			return 0;
		}
		else if(n.right != null && n.left != null) {
			return 2;
		}
		else if(n.right == null && n.left != null) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	private Node<E> getLeftBigest(Node<E> n) {
		Node<E> tmp = n;
		
		while(tmp.left != null) {
			tmp = tmp.left;
		}
		
		return tmp;
	}
	
	public void printTree() {
		
	}
}
