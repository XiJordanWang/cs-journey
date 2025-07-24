package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public LinkedListDeque61B() {
        Node node = new Node(null, null, null);
        this.first = node;
        this.last = node;
        this.size = 0;
    }

    private Node first;
    private Node last;
    private int size;

    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void addFirst(T x) {
        if (this.first.item == null) {
            this.first = new Node(x, null, null);
            this.last = this.first;
        } else {
            Node node = new Node(x, null, this.first);
            this.first.prev = node;
            this.first = node;
        }
        this.size++;
    }

    @Override
    public void addLast(T x) {
        if (this.last.item == null) {
            this.last = new Node(x, null, null);
            this.first = this.last;
        } else {
            Node node = new Node(x, this.last, null);
            this.last.next = node;
            this.last = node;
        }
        this.size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        list.add(this.first.item);
        Node node = this.first;
        while (node.next != null) {
            list.add(node.next.item);
            node = node.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T item = this.first.item;
        if (this.first.item != null) {
            this.first = this.first.next;
            this.first.prev = null;
            this.size--;
        }
        return item;
    }

    @Override
    public T removeLast() {
        T item = this.last.item;
        if (this.last.item != null) {
            this.last = this.last.prev;
            this.last.next = null;
            this.size--;
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size()) {
            return null;
        }
        Node node = this.first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }


    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size()) {
            return null;
        }
        return getHelper(this.first, index);
    }

    private T getHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getHelper(node.next, index - 1);
    }
}
