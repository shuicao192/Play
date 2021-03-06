public class SelectionSortData {

    private final int[] numbers;

    public int orderedIndex = -1;
    public int currentCompareIndex = -1;
    public int currentMinIndex = -1;

    public SelectionSortData(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound + 1);
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public static void main(String[] args) {
        SelectionSortData data = new SelectionSortData(30,100);

        for (int i = 0; i < data.N(); i++) {
            System.out.println(data.numbers[i]);
        }
    }

    public int getNumber(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }
        return numbers[index];
    }

    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

    public int N(){
        return numbers.length;
    }
}
