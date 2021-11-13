public class InsertionSortData {

    private final int[] numbers;

    public InsertionSortData(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound + 1);
        }
    }

    public static void main(String[] args) {
        InsertionSortData data = new InsertionSortData(30,100);
        
        for (int i = 0; i < data.N(); i++) {
            for (int j = i; j > 0 && data.getNumber(j) < data.getNumber(j - 1); j--) {
                data.swap(j, j - 1);
            }
        }
        for (int i = 0; i < data.N(); i++) {
            System.out.println(data.numbers[i]);
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
