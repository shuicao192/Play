public class QuickSort {

    int N;
    private final int[] arr;

    public QuickSort(int N, int randomBound) {
        this.N = N;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) ((Math.random() * randomBound) + 1);
        }
    }

    public void quickSort() {
        quickSort(0, N - 1);
    }

    private void quickSort(int l, int r) {
        if (l >= r) return;

        int index = partition(l, r);
        quickSort(l, index -1);
        quickSort(index + 1, r);

    }

    public int partition(int l, int r) {
        int num = arr[l];
        int cursor = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < num) {
                cursor++;
                swap(cursor, i);
            }
        }
        swap(l, cursor);
        return cursor;
    }

    public void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print() {
        for (int j : arr) {
            System.out.println(j);
        }
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort(100, 200);
        quickSort.quickSort();
        quickSort.print();
    }
}
