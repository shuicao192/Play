public class QuickSortThreeWay {
    int N;
    private final int[] arr;

    public QuickSortThreeWay(int N, int randomBound) {
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

        int cursor = (int) (Math.random() * (r - l + 1) + l);
        swap(l, cursor);
        int num = arr[l];
        int i = l + 1;
        int lt = l;
        int rt = r + 1;
        while (i < rt) {
            if (arr[i] < num) {
                swap(i, lt + 1);
                i++;
                lt++;
            }else if (arr[i] > num) {
                swap(i, rt -1);
                rt--;
            }else {
                i++;
            }
        }
        swap(l,lt);

        quickSort(l, lt -1);
        quickSort(rt, r);

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
        QuickSortThreeWay quickSort = new QuickSortThreeWay(100, 200);
        quickSort.quickSort();
        quickSort.print();
    }
}
