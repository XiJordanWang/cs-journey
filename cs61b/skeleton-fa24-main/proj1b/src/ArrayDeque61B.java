import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;

    private int firstIndex;
    private int lastIndex;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        firstIndex = 0;
        lastIndex = 1;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int curr = plusOne(firstIndex);
        for (int i = 0; i < size; i++) {
            newItems[i + 1] = items[curr];
            curr = plusOne(curr);
        }
        items = newItems;
        firstIndex = 0;
        lastIndex = size + 1;
    }

    private void checkDownsize() {
        if (items.length > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length - 1) {
            resize(size * 2);
        }
        items[firstIndex] = x;
        firstIndex = minusOne(firstIndex);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length - 1) {
            resize(items.length * 2);
        }
        items[lastIndex] = x;
        lastIndex = plusOne(lastIndex);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        int curr = plusOne(firstIndex);
        for (int i = 0; i < size; i++) {
            result.add(items[curr]);
            curr = plusOne(curr);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        firstIndex = plusOne(firstIndex);
        T firstItem = items[firstIndex];
        items[firstIndex] = null;
        size--;
        checkDownsize();
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (items.length > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        lastIndex = minusOne(lastIndex);
        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        size--;
        checkDownsize();
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 && index > size()) {
            return null;
        }
        return toList().get(index);
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
