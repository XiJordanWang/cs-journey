import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;  // current selected word
        int count = 0;           // number of words read so far

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;
            // With probability 1/count, replace champion
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
        }

        StdOut.println(champion);
    }
}