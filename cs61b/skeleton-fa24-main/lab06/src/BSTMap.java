import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BST<K, V> root;

    private static class BST<K extends Comparable<K>, V> {
        K key;
        V value;
        BST<K, V> leftChild;
        BST<K, V> rightChild;

        public BST(K key, V value) {
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BST<>(key, value);
            return;
        }
        putHelper(root, key, value);
    }

    private void putHelper(BST<K, V> tree, K key, V value) {
        if (tree.key.equals(key)) {
            tree.value = value;
            return;
        }
        if (tree.key.compareTo(key) > 0) {
            if (tree.leftChild == null) {
                tree.leftChild = new BST<>(key, value);
                return;
            }
            putHelper(tree.leftChild, key, value);
        } else {
            if (tree.rightChild == null) {
                tree.rightChild = new BST<>(key, value);
                return;
            }
            putHelper(tree.rightChild, key, value);
        }
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BST<K, V> tree, K key) {
        if (tree == null) return null;
        if (tree.key.equals(key)) return tree.value;
        V v = getHelper(tree.leftChild, key);
        if (v != null) return v;
        return getHelper(tree.rightChild, key);
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BST<K, V> tree, K key) {
        if (tree == null) return false;
        if (tree.key.equals(key)) return true;
        boolean v = containsKeyHelper(tree.leftChild, key);
        if (v) return true;
        return containsKeyHelper(tree.rightChild, key);
    }

    @Override
    public int size() {
        return sizeHelper(root, 0);
    }

    private int sizeHelper(BST<K, V> tree, int size) {
        if (tree == null) return size;
        size++;
        size = sizeHelper(tree.leftChild, size);
        size = sizeHelper(tree.rightChild, size);
        return size;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        return keySetHelper(root, new TreeSet<>());
    }

    private Set<K> keySetHelper(BST<K, V> tree, Set<K> set) {
        if (tree == null) return set;
        keySetHelper(tree.leftChild, set);
        set.add(tree.key);
        keySetHelper(tree.rightChild, set);
        return set;
    }

    @Override
    public V remove(K key) {
        V v = get(key);
        if (v == null) return null;
        root = removeHelper(root, key);
        return v;
    }

    private BST<K, V> removeHelper(BST<K, V> tree, K key) {
        if (tree == null) return null;
        int cmp = key.compareTo(tree.key);
        if (cmp < 0) {
            tree.leftChild = removeHelper(tree.leftChild, key);
        } else if (cmp > 0) {
            tree.rightChild = removeHelper(tree.rightChild, key);
        } else {
            if (tree.leftChild == null) return tree.rightChild;
            if (tree.rightChild == null) return tree.leftChild;

            BST<K, V> successor = min(tree.rightChild);
            tree.key = successor.key;
            tree.value = successor.value;
            tree.rightChild = deleteMin(tree.rightChild);
        }
        return tree;
    }

    private BST<K, V> min(BST<K, V> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    private BST<K, V> deleteMin(BST<K, V> node) {
        if (node.leftChild == null) return node.rightChild;
        node.leftChild = deleteMin(node.leftChild);
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(BST<K, V> tree) {
        if (tree == null) {
            return;
        }
        printHelper(tree.leftChild);
        System.out.println("tree = " + tree);
        printHelper(tree.rightChild);
    }
}