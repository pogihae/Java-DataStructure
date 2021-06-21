
import Sorting.Sort;

public class HelloWorld {

	public static void main(String[] args) {
		//System.out.println("Hello World");
		
		String[] arr = new String[] {
				"a", "f", "b", "c", "d", "g", "z"
		};
		
		Sort.quickSort(arr, 0, arr.length-1, true);
		
		for(String i : arr)
			System.out.print(i+" ");
		
	}

}
