package symboltable;

/** Binary Search Tree <br>
 * @complexity logN ~ N
 */
public class BinarySearchTree<K extends Comparable<K>, V extends Comparable<V>> {
	private static class Node<K extends Comparable<K>, V extends Comparable<V>> {
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;
		int childNum;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			childNum = 1;
		}
	}

	/*field*/
	private Node<K, V> root;

	/*constructor*/
	public BinarySearchTree() {
		root = null;
	}

	/*put*/
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	private Node<K,V> put(Node<K,V> node, K key, V value) {
		if(node == null) return new Node<>(key, value);

		int cmp = key.compareTo(node.key);

		if(cmp > 0) node.right = put(node.right, key, value);
		else if(cmp < 0) node.left = put(node.left, key, value);
		else node.value = value;

		//to chk
		node.childNum++;

		return node;
	}

	/*get*/
	public V get(K key) {
		Node<K,V> node = get(root, key);
		return (node == null)? null : node.value;
	}
	private Node<K,V> get(Node<K,V> node, K key) {
		if(node == null) return null;

		int cmp = key.compareTo(node.key);
		if(cmp > 0) return get(node.right, key);
		else if(cmp < 0) return get(node.left, key);

		return node;
	}

	/*delete*/
	public void del(K key) {
		root = del(root, key);
	}

	private Node<K,V> del(Node<K,V> node, K key) {
		if(node == null) return null;

		int cmp = key.compareTo(node.key);

		if(cmp > 0) node.right = del(node.right, key);
		else if(cmp < 0) node.left = del(node.left, key);
		else {
			if(node.right == null) return node.left;
			if(node.left == null) return node.right;

			Node<K,V> tmp = node;
			node = min(tmp.right);
			node.right = delMin(tmp.right);
			node.left = tmp.left;
		}

		node.childNum = size(node.left) + size(node.right) + 1;
		return node;
	}

	public void delMin() {
		root = delMin(root);
	}
	private Node<K,V> delMin(Node<K,V> node) {
		if(node == null) return null;
		if(node.left == null) return node.right;
		node.left = delMin(node.left);
		node.childNum = size(node.left) + size(node.right) + 1;
		return node;
	}

	/*min, max*/
	public K min() {
		Node<K,V> n = min(root);
		return (n == null)? null : n.key;
	}
	private Node<K,V> min(Node<K,V> node) {
		if(node == null) return null;
		if(node.left != null) return min(node.left);
		return node;
	}

	public K max() {
		return max(root);
	}
	private K max(Node<K,V> node) {
		if(node == null) return null;
		if(node.right != null) return max(node.right);
		return node.key;
	}

	/*floor, ceiling*/
	public K floor(K key) {
		Node<K,V> tmp = floor(root, key);
		return (tmp == null)? null : tmp.key;
	}
	private Node<K,V> floor(Node<K,V> node, K key) {
		if(node == null) return null;

		int cmp = key.compareTo(node.key);

		if(cmp < 0) return floor(node.left, key);
		else if(cmp == 0) return node;

		Node<K,V> n = floor(node.right, key);
		if(n != null) return n;
		else return node;
	}

	/*rank*/
	public int rank(K key) {
		return rank(root, key);
	}
	private int rank(Node<K,V> node, K key) {
		if(node == null) return 0;

		int cmp = key.compareTo(node.key);
		if(cmp > 0) return 1 + size(node.left) + rank(node.right, key);
		else if(cmp < 0) return rank(node.left, key);

		return size(node.left);
	}

	/*range search*/

	/*size*/
	public int size() {
		return size(root);
	}
	private int size(Node<K,V> node) {
		return (node == null)? 0 : node.childNum;
	}

	//test
	public static void main(String[] args) {
		BinarySearchTree<Character, Integer> bst = new BinarySearchTree<>();

		for(int i=0; i<50; i++) {
			Character ran = (char)((Math.random() * 26) + 'A');
			bst.put(ran, i);
		}

		System.out.println(bst.min());
		System.out.println(bst.max());

		for(int i=0; i<10; i++) {
			Character ran = (char)((Math.random() * 26) + 'A');
			bst.del(ran);
		}

		System.out.println(bst.min());
		System.out.println(bst.max());

		System.out.println(bst.size());

	}
}
