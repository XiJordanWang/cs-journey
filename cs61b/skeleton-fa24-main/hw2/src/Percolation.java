import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int N;
    private Site[][] sites;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        this.N = N;
        sites = new Site[N][N];
        int index = 0;
        uf = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < sites.length; i++) {
            uf.union(0, i);
            for (int j = 0; j < sites[i].length; j++) {
                sites[i][j] = new Site(index, false);
                index++;
            }
        }
    }

    public void open(int row, int col) {
        Site site = sites[row][col];
        site.isOpen = true;

        // Up
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(getSite(row - 1, col).index, site.index);
        }
        // Down
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(getSite(row + 1, col).index, site.index);
        }
        // Left
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(getSite(row, col - 1).index, site.index);
        }
        // Right
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(getSite(row, col + 1).index, site.index);
        }
    }

    public boolean isOpen(int row, int col) {
        return sites[row][col].isOpen;
    }

    public boolean isFull(int row, int col) {
        return isOpen(row, col) && uf.connected(0, sites[row][col].index);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (Site[] site : sites) {
            for (Site s : site) {
                if (s.isOpen) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i)) {
                return true;
            }
        }
        return false;
    }

    static class Site {
        int index;
        boolean isOpen;

        public Site(int index, boolean isOpen) {
            this.index = index;
            this.isOpen = isOpen;
        }
    }

    private Site getSite(int row, int col) {
        return sites[row][col];
    }
}
