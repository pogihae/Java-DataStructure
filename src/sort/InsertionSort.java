package sort;


/**Insertion Sort<br>
 * @stable Yes
 * @complexity N ~ N^2
 * */
public class InsertionSort extends Sort {
	@Override
	public void sort(Comparable[] arr) {
		int len = arr.length;
		for(int i=1; i<len; i++) {
			Comparable toInsert = arr[i];
			int j;
			for(j=i; j>0 && less(toInsert, arr[j-1]); j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = toInsert;
		}
	}

	public static void main(String[] args) {
		InsertionSort insertionSort = new InsertionSort();
		Integer[] arr = insertionSort.makeIntArray(30);
		insertionSort.sort(arr);
		insertionSort.print(arr);
	}
}
