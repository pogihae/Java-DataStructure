package Sorting;


/**
 * @author tir29
 * todo:  multithreading
 */
public class Sort {
	
	/**
	 * 
	 * Sort object array
	 * original will be changed
	 * 
	 * @param Target array
	 * @param ascd, true: ascending, false: descending
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] arr, boolean ascd) {
		int len = arr.length;
		int j = 0;
		T toInsert = arr[0];
		
		//to add while i: 1 ~ n
		for(int i=1; i<len; i++) {
			toInsert = arr[i];
			//location while j: i-1 ~ 0
			for(j= i-1; j>-1; j--) {
				//copy j to j+1
				arr[j+1] = arr[j];
				//compare j with toInsert
				if(compare(arr[j], toInsert, ascd)) {
					break;
				}
			}
			//insert j+1
			arr[j+1] = toInsert;
		}
	}
	
	public static <T extends Comparable<T>> void quickSort(T[] arr, int first, int last, boolean ascd) {
		if(first >= last) {
			return;
		}
		
		int pivot = partition(arr, first, last, ascd);
		
		quickSort(arr, first, pivot-1, ascd);
		quickSort(arr, pivot+1, last, ascd);
	}
	
	
	/**
	 * 
	 * @param arr: full array
	 * @param first: left index
	 * @param last: right index
	 * @param is ascending order
	 * @return pivot index
	 */
	private static <T extends Comparable<T>> int partition(T[] arr, int first, int last, boolean ascd) {
		T pivot = arr[first];
		int leftP = first+1;
		int rightP = last;
		
		//left <= right
		while(leftP <= rightP) {
			while(leftP <= last && compare(arr[leftP], pivot, !ascd)) leftP++;
			
			while(compare(arr[rightP], pivot, ascd) && rightP >= first+1) rightP--;
			
			if(leftP < rightP) {
				swap(arr, leftP, rightP);
			}
		}
		swap(arr, first, rightP);
		return rightP;
	}
	
	/**swap element*/
	private static <T extends Comparable<T>> void swap(T[] arr, int x, int y) {
		T tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	/**
	 * @param first elem
	 * @param second elem
	 * @param is ascending order?
	 * @return each order compare by boolean
	 */
	private static <T extends Comparable<T>> boolean compare(T x, T y, boolean ascd) {
		if(ascd)
			return x.compareTo(y) >= 0;
		else
			return x.compareTo(y) <= 0;
	}
}
