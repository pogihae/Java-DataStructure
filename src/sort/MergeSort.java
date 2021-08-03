package sort;

/**Merge Sort<br>
 * @stable Yes
 * @complexity NlogN
 * */
public class MergeSort extends Sort{
    private Comparable[] tmp;

    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        tmp = new Comparable[len];
        sort(arr, 0, (len-1)/2, len-1);
    }
    void sort(Comparable[] arr, int lo, int mid, int hi){
        if(lo >= hi) return;

        sort(arr, lo, (lo+mid)/2, mid);
        sort(arr, mid+1, (mid+1+hi)/2, hi);
        merge(arr, lo, mid, hi);
    }
    void merge(Comparable[] arr, int lo, int mid, int hi){
        int i = lo;
        int j = mid+1;

        for(int k=lo; k<=hi; k++) {
            if(i > mid) tmp[k] = arr[j++];
            else if(j > hi) tmp[k] = arr[i++];
            else if(less(arr[i], arr[j])) tmp[k] = arr[i++];
            else tmp[k] = arr[j++];
        }

        for(int k=lo; k<=hi; k++) {
            arr[k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        Integer[] arr = mergeSort.makeIntArray(29);
        mergeSort.sort(arr);
        mergeSort.print(arr);
    }
}
