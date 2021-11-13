public class ThreeGatesExperiment {

    private int N;

    public ThreeGatesExperiment(int N) {
        if(N <= 0)
            throw new IllegalArgumentException("N must be larger than 0!");

        this.N = N;
    }

    public void run(boolean changeChoice) {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeChoice)) wins++;
        }

        System.out.println(changeChoice ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins/N);
    }

    private boolean play(boolean changeChoice) {
        int prizeDoor = (int)(Math.random() * 3);
        int playerChoice = (int)(Math.random() * 3);

        if (playerChoice == prizeDoor) {
            return !changeChoice;
        } else {
            return changeChoice;
        }
    }

    public static void main(String[] args) {
        int N = 10000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);

        exp.run(true);
        System.out.println();
        exp.run(false);
    }
}
