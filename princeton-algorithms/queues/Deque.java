import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
        this.size = 0;
    }

    private static class Node<Item> {
        Item value;
        Node<Item> prev;
        Node<Item> next;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("The argument cannot be null!");
        Node<Item> node = new Node<>();
        node.value = item;
        if (isEmpty()) {
            initializeNode(node);
            return;
        }
        first.prev = node;
        node.next = first;
        first = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("The argument cannot be null!");
        Node<Item> node = new Node<>();
        node.value = item;
        if (isEmpty()) {
            initializeNode(node);
            return;
        }
        last.next = node;
        node.prev = last;
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty!");
        Item value = first.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty!");
        Item value = last.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There are no more items to return");
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported!");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        StdOut.printf("Initialize deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        deque.addFirst("A");
        StdOut.printf("Add first to deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        deque.addFirst("B");
        StdOut.printf("Add first to deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        deque.addFirst("C");
        StdOut.printf("Add first to deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        StdOut.printf("Removed item: %s\n", deque.removeFirst());
        deque.addLast("B");
        StdOut.printf("Add last to deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        deque.addLast("A");
        StdOut.printf("Add last to deque, size: %d, isEmpty: %b\n", deque.size, deque.isEmpty());
        StdOut.printf("Removed item: %s\n", deque.removeLast());

        for (String s : deque) {
            StdOut.println(s);
        }
    }


    private void initializeNode(Node<Item> node) {
        first = node;
        last = node;
        size++;
    }
}
