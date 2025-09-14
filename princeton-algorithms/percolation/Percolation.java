import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF ufPercolation;
    private final WeightedQuickUnionUF ufFullness;
    private final boolean[] sites;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n cannot less or equal than 0");

        this.n = n;
        this.ufPercolation = new WeightedQuickUnionUF(n * n + 2);
        this.ufFullness = new WeightedQuickUnionUF(n * n + 1);
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;
        this.openCount = 0;

        sites = new boolean[n * n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        catchArgumentException(row, col);
        int index = getIndex(row, col);
        if (sites[index]) {
            return;
        }
        openCount++;
        sites[index] = true;

        if (row == 1) {
            ufPercolation.union(index, virtualTop);
            ufFullness.union(index, virtualTop);
        }
        if (row == n) ufPercolation.union(index, virtualBottom);

        // up
        if (row > 1 && isOpen(row - 1, col)) {
            ufPercolation.union(index, getIndex(row - 1, col));
            ufFullness.union(index, getIndex(row - 1, col));
        }
        ;
        // down
        if (row < n && isOpen(row + 1, col)) {
            ufPercolation.union(index, getIndex(row + 1, col));
            ufFullness.union(index, getIndex(row + 1, col));
        }
        ;
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            ufPercolation.union(index, getIndex(row, col - 1));
            ufFullness.union(index, getIndex(row, col - 1));
        }

        // right
        if (col < n && isOpen(row, col + 1)) {
            ufPercolation.union(index, getIndex(row, col + 1));
            ufFullness.union(index, getIndex(row, col + 1));
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
        return isOpen(row, col) && ufFullness.find(index) == ufFullness.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufPercolation.find(virtualBottom) == ufPercolation.find(virtualTop);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);

        p.open(1, 1);
        p.percolates();
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
