package sort;


/**
 * @author tir29
 * todo:  multithreading
 */
public class InsertionSort {
	
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
