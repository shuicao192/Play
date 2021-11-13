import java.util.Random;

public class HeapSort {

    int N;
    private final int[] arr;

    public HeapSort(int N, int randomBound) {
        this.N = N;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) ((Math.random() * randomBound) + 1);
//            arr[i] = N - i;
        }
    }

    public void heapSort() {
        //构建最大堆
        for( int i = (N-1-1)/2 ; i >= 0 ; i -- ){
            shiftDown(N, i);
        }
        // 堆排序
        for( int i = N-1; i > 0 ; i-- ){
            swap(0, i);
            shiftDown(i, 0);
        }
    }

    private void shiftDown(int n, int k){

        while( 2*k+1 < n ){
            int j = 2*k+1;
            if( j+1 < n && arr[j + 1] > arr[j] )
                j += 1;

            if( arr[k] >= arr[j] )
                break;

            swap(k, j);

            k = j;
        }
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
        HeapSort quickSort = new HeapSort(100, 200);
        quickSort.heapSort();
        quickSort.print();
    }
}
