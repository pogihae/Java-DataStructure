package sort;

/**Selection Sort<br>
 * @stable No
 * @complexity N^2
 * */
public class SelectionSort extends Sort {
    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        for(int i=0; i<len; i++) {
            exch(arr, i, min(arr, i, len-1));
        }
    }

    /*return min index*/
    int min(Comparable[] arr, int lo, int hi) {
        int result = lo;
        for(int i=lo+1; i<=hi; i++) {
            if(arr[result].compareTo(arr[i]) > 0) result = i;
        }
        return result;
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        Integer[] arr = selectionSort.makeIntArray(30);
        selectionSort.sort(arr);
        selectionSort.print(arr);
    }
}
