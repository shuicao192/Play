public class InsertionSortData {

    private final int[] numbers;

    public int orderedIndex = -1;
    public int currentIndex = -1;

    public InsertionSortData(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound + 1);
        }
    }

    public int getNumber(int index) {
        return numbers[index];
    }

    public void swap(int i, int j) {
        if(( i < 0 || i >= numbers.length) || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

    public int N(){
        return numbers.length;
    }
}
