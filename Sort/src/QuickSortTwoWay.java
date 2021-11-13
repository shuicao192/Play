public class QuickSortTwoWay {

    int N;
    private final int[] arr;

    public QuickSortTwoWay(int N, int randomBound) {
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
        int cursor = (int) (Math.random() * (r - l + 1) + l);
        swap(l, cursor);
        int num = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] < num) {
                i++;
            }
            while (j >= l + 1 && arr[j] > num) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(i,j);
            i++;
            j--;
        }
        swap(l,j);
        return j;
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
        QuickSortTwoWay quickSort = new QuickSortTwoWay(100, 200);
        quickSort.quickSort();
        quickSort.print();
    }
}
