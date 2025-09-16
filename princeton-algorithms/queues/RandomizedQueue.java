import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] arr;
    private int arrSize = 10;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[arrSize];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("The argument cannot be null!");
        arr[size] = item;
        size++;
        if (size == arrSize) resize(arrSize * 2);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The RandomizedQueue is empty!");
        int i = StdRandom.uniformInt(size);
        Item item = arr[i];
        arr[i] = arr[size - 1];
        arr[size - 1] = null;
        size--;
        if (size > 0 && size == arrSize / 4) resize(arrSize / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The RandomizedQueue is empty!");
        int i = StdRandom.uniformInt(size);
        return this.arr[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private final Item[] copy;
        private int current = 0;

        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[size];
            System.arraycopy(arr, 0, copy, 0, size);
            StdRandom.shuffle(copy);
        }

        @Override
        public boolean hasNext() {
            return current < copy.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There are no more items to return");
            return copy[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method is not supported!");
        }
    }

    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        arrSize = newSize;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        randomizedQueue.enqueue("D");
        randomizedQueue.enqueue("E");
        randomizedQueue.enqueue("F");
        randomizedQueue.enqueue("G");
        randomizedQueue.enqueue("H");
        randomizedQueue.enqueue("I");
        randomizedQueue.enqueue("J");

        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        randomizedQueue.enqueue("K");
        randomizedQueue.enqueue("L");

        for (String s : randomizedQueue) {
            StdOut.println(s);
        }


        StdOut.printf("Sample: %s\n", randomizedQueue.sample());
        StdOut.printf("Sample: %s\n", randomizedQueue.sample());
        StdOut.printf("Sample: %s\n", randomizedQueue.sample());
        StdOut.printf("Sample: %s\n", randomizedQueue.sample());
        StdOut.printf("Sample: %s\n", randomizedQueue.sample());


        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Size: %d\n", randomizedQueue.size());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Deque: %s\n", randomizedQueue.dequeue());
        StdOut.printf("Size: %d\n", randomizedQueue.size());
    }
}