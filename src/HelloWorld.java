import Tree.BinarySearchTree;

public class HelloWorld {

	public static void main(String[] args) {
		//System.out.println("Hello World");
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		for(int i=0; i<20; i++) {
			int toAdd = (int)(Math.random()*100) + 1;
			if(bst.isExist(toAdd))
				continue;
			bst.add(toAdd);
		}
		
		
		bst.printTree();
		
	}

}
