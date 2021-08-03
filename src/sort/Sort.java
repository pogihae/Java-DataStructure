package sort;

/**Sort template<br>
 * to change priority(default: min is higher) override less method
 * */
public abstract class Sort {
    public abstract void sort(Comparable[] arr);

    public boolean isSorted(Comparable[] arr) {
        int len = arr.length;
        for(int i=1; i<len; i++) {
            if(less(arr[i],arr[i-1])) return false;
        }
        return true;
    }

    public boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    public void exch(Comparable[] arr, int xIdx, int yIdx) {
        Comparable tmp = arr[xIdx];
        arr[xIdx] = arr[yIdx];
        arr[yIdx] = tmp;
    }


    /*test*/
    public Integer[] makeIntArray(int len) {
        Integer[] arr = new Integer[len];
        for(int i=0; i<len; i++) {
            arr[i] = (int)(Math.random() * (len*10));
        }
        return arr;
    }

    public void print(Comparable[] arr) {

        System.out.println("------" + "Sorted: "+ isSorted(arr)+ "-------");
        for(Comparable x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("-------------------------");
    }
}
