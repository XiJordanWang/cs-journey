public class Calculator {

    public static void main(String[] args) {
        int[] scores = {60, 71, 67, 70, 80, 75, 82, 91, 92, 90, 88, 90, 77, 78, 91, 89, 98, 80};

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        System.out.println("avg = " + sum / scores.length);
    }
}
