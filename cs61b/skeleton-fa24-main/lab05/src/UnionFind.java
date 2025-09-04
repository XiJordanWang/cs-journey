import java.util.Arrays;

public class UnionFind {
    private int[] arr;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        arr = new int[N];
        Arrays.fill(arr, -1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (arr[v] < 0) {
            return arr[v];
        }
        return sizeOf(parent(v));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return arr[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v > arr.length) {
            throw new IllegalArgumentException();
        }
        if (arr[v] < 0) {
            return v;
        }
        return find(parent(v));
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 > arr.length || v2 > arr.length) {
            throw new IllegalArgumentException();
        }
        if (v1 == v2) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(v1) == sizeOf(v2)) {
            arr[root2] = arr[root2] + sizeOf(v1);
            arr[root1] = root2;
        } else if (sizeOf(v1) < sizeOf(v2)) {
            arr[root1] = arr[root1] + sizeOf(v2);
            arr[root2] = root1;
        }
    }
}
