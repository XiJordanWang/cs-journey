import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private WeightedQuickUnionUF uf;
    private boolean[] sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot less or equal than 0");
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n);
        sites = new boolean[n * n];
        for (int i = 0; i < n; i++) {
            uf.union(i, n - 1);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        catchArgumentException(row, col);
        int index = getIndex(row, col);
        if (sites[index]) {
            return;
        }
        sites[index] = true;
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(index, index - n);
        }
        // down
        if (row < n - 1 && isOpen(row + 1, col)) {
            uf.union(index, index + n);
        }
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(index, index - 1);
        }
        // right
        if (col < n - 1 && isOpen(row, col + 1)) {
            uf.union(index, index + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        catchArgumentException(row, col);
        int index = getIndex(row, col);
        return sites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        catchArgumentException(row, col);
        int index = getIndex(row, col);
        return isOpen(row, col) && uf.find(index) == 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean status : sites) {
            if (status) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (isFull(n, i + 1)) return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);

        p.open(1, 1);
        StdOut.println("1,1 opened: " + p.isOpen(1, 1));
        StdOut.println("1,1 is full: " + p.isFull(1, 1));

        p.open(2, 2);
        StdOut.println("2,2 opened: " + p.isOpen(2, 2));
        StdOut.println("2,2 is full: " + p.isFull(2, 2));

        p.open(2, 3);
        StdOut.println("2,3 opened: " + p.isOpen(2, 3));
        StdOut.println("2,3 is full: " + p.isFull(2, 3));

        p.open(1, 2);
        StdOut.println("1,2 opened: " + p.isOpen(1, 2));
        StdOut.println("1,2 is full: " + p.isFull(2, 2));
        StdOut.println("2,2 is full: " + p.isFull(2, 2));
        StdOut.println("2,3 is full: " + p.isFull(2, 3));

        p.open(3, 2);
        StdOut.println("3,2 opened: " + p.isOpen(3, 2));
        StdOut.println("3,2 is full: " + p.isFull(3, 2));
        StdOut.println("Percolate : " + p.percolates());

        StdOut.println("Opened sites number: " + p.numberOfOpenSites());
    }

    private void catchArgumentException(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException("The row or col is illegal.");
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col - 1;
    }
}
