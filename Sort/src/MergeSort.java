import java.util.Arrays;

public class MergeSort {

    private final Comparable[] arr;

    public MergeSort(Comparable[] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        int N = 100;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Integer(N-i);
        }
        MergeSort mergeSort = new MergeSort(arr);
        mergeSort.sort();
        mergeSort.print();
    }

    public void sort() {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private void sort(Comparable[] arr, int l, int r) {

        if (l >= r) return;

        int mid = (l + r) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private void merge(Comparable[] arr, int l, int mid, int r) {

        Comparable[] aux = Arrays.copyOfRange(arr, l, r+1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i-l].compareTo(aux[j-l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    private void print() {
        for (Comparable comparable : arr) {
            System.out.println(comparable);
        }
    }
}
