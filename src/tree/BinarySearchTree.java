package tree;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 
 * This class is for BST
 * use comparable to implement
 * use BFS for print
 * 
 * @author tir29
 * @param <E>
 */
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
	
	/*constructor*/
	public BinarySearchTree() {
		this.root = null;
	}
	
	public BinarySearchTree(E root) {
		this.root = new Node<>(root);
	}
	
	/**
	 * 
	 * add data to tree
	 * with remain BST
	 * 
	 * @param to add data
	 */
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
	
	
	/**
	 * 
	 * remove data from tree
	 * if removed has two child
	 * copy left most child
	 * and remove that leaf node
	 * 
	 * @param to remove
	 * @return removed data
	 */
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
	
	/**
	 * 
	 * get node's child number
	 * 
	 * @param node
	 * @return 0 | 1: only left | -1: only right | 2
	 */
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
	
	/**
	 * 
	 * get Bigest node in left sub tree
	 * 
	 * @param sub tree's root
	 * @return left bigest node
	 */
	private Node<E> getLeftBigest(Node<E> n) {
		Node<E> tmp = n;
		
		while(tmp.left != null) {
			tmp = tmp.left;
		}
		
		return tmp;
	}
	
	
	/**
	 * 
	 * @param for check data
	 * @return true if existed
	 */
	public boolean isExist(E e) {
		return isExist(root, e);
	}
	
	/**
	 * 
	 * encapsule for private node
	 * 
	 * @param root, for recursive implement
	 * @param e
	 * @return
	 */
	private boolean isExist(Node<E> n, E e) {
		if(n == null) {
			return false;
		}
		
		int cmp = n.data.compareTo(e);
		
		if(cmp == 0) {
			return true;
		}
		else if(cmp > 0) {
			return isExist(n.left, e);
		}
		else {
			return isExist(n.right, e);
		}
	}
	
	/**
	 * print tree by using bst
	 * line for level
	 */
	public void printBinaryTree() {
		Queue<Node<E>> que = new LinkedList<>();
		
		que.add(root);
		
		Node<E> cur = null;
		int curLevel = 1;
		int nextLevel = 0;
		while(!que.isEmpty()) {
			cur = que.poll();
			curLevel--;
			
			if(cur.left != null) {
				que.add(cur.left);
				nextLevel++;
			}
			if(cur.right != null) {
				que.add(cur.right);
				nextLevel++;
			}
			//visit
			System.out.print(cur+" ");
			
			if(curLevel == 0) {
				System.out.println();
				curLevel = nextLevel;
				nextLevel = 0;
			}
		}
		
	}
}
