import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(dimension()).append("\n");
        for (int[] tile : tiles) {
            for (int i : tile) {
                result.append(i).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int expected = dimension() * i + j + 1;
                if (i == dimension() - 1 && j == dimension() - 1) expected = 0;
                int tile = tiles[i][j];
                if (tile != 0 && tile != expected) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int tile = tiles[i][j];
                if (tile != 0) {
                    int correctColum = (tiles[i][j] - 1) % dimension();
                    int correctRow = (tiles[i][j] - 1) / dimension();
                    count += Math.abs(correctColum - i) + Math.abs(correctRow - j);
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int expected = dimension() * i + j + 1;
                if (i == dimension() - 1 && j == dimension() - 1) expected = 0;
                if (tiles[i][j] != expected) return false;
            }
        }
        return true;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] == 0) {
                    // move up
                    if (i != 0) {
                        int[][] copy = copyTiles();
                        int num = copy[i - 1][j];
                        copy[i - 1][j] = copy[i][j];
                        copy[i][j] = num;
                        neighbors.add(new Board(copy));
                    }
                    // move down
                    if (i != dimension() - 1) {
                        int[][] copy = copyTiles();
                        int num = copy[i + 1][j];
                        copy[i + 1][j] = copy[i][j];
                        copy[i][j] = num;
                        neighbors.add(new Board(copy));
                    }
                    // move left
                    if (j != 0) {
                        int[][] copy = copyTiles();
                        int num = copy[i][j - 1];
                        copy[i][j - 1] = copy[i][j];
                        copy[i][j] = num;
                        neighbors.add(new Board(copy));
                    }
                    // move right
                    if (j != dimension() - 1) {
                        int[][] copy = copyTiles();
                        int num = copy[i][j + 1];
                        copy[i][j + 1] = copy[i][j];
                        copy[i][j] = num;
                        neighbors.add(new Board(copy));
                    }
                }
            }
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int n = dimension();
        int[][] copy = copyTiles();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (copy[i][j] != 0 && copy[i][j + 1] != 0) {
                    int temp = copy[i][j];
                    copy[i][j] = copy[i][j + 1];
                    copy[i][j + 1] = temp;
                    return new Board(copy);
                }
            }
        }
        return null;
    }

    private int[][] copyTiles() {
        int n = dimension();
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        int num = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = num;
                num++;
            }
        }

        Board board = new Board(tiles);
        String string = board.toString();
        System.out.println("string = " + string);
        System.out.println("hamming = " + board.hamming());
        System.out.println("manhaton = " + board.manhattan());

        Iterable<Board> neighbors = board.neighbors();
        for (Board neighbor : neighbors) {
            System.out.println("neighbor.toString() = " + neighbor.toString());
        }
    }
}
