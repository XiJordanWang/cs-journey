import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }
        for (int j = 0; j < i; j++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
