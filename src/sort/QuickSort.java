package sort;

/**Quick Sort<br>
 * @stable NO
 * @complexity NlogN
 * */
public class QuickSort extends Sort{
	@Override
	public void sort(Comparable[] arr) {
		int len = arr.length;
		sort(arr, 0, len-1);
	}
	void sort(Comparable[] arr, int lo, int hi) {
		if(lo >= hi) return;

		int pivot = partition(arr, lo, hi);
		sort(arr, lo, pivot-1);
		sort(arr, pivot+1, hi);
	}

	int partition(Comparable[] arr, int first, int last) {
		int pivot = first;
		int lo = first+1;
		int hi = last;

		while(lo <= hi) {
			while(less(arr[lo], arr[pivot]) && lo < last) { lo++; }
			while(less(arr[pivot], arr[hi])) { hi--; }
			if(lo >= hi) break;
			exch(arr, lo, hi);
		}
		exch(arr, pivot, hi);
		return hi;
	}

	public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();
		Integer[] arr = quickSort.makeIntArray(30);
		quickSort.sort(arr);
		quickSort.print(arr);
	}
}
