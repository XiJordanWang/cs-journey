/**
 * This is a fill in the blanks version of the SLList class
 * in case you want to try to figure out how to write it yourself.
 * After writing your methods, you can run the AListTest file.
 */
public class AList {

    private int[] items;
    private int size;

    /**
     * Creates an empty list.
     */
    public AList() {
        items = new int[100];
        size = 0;
    }

    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(int x) {
        if (size == items.length) {
            resize(size + 100);
        }
        items[size()] = x;
        size++;
    }

    /**
     * Returns the item from the back of the list.
     */
    public int getLast() {
        return items[size() - 1];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public int get(int i) {
        return items[i];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public int removeLast() {
        int last = getLast();
        items[size() - 1] = 0;
        size--;
        return last;
    }
}
