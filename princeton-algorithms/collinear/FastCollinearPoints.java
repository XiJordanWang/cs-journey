import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Point array is null");

        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point is null");
        }

        Point[] checkDuplicate = points.clone();
        Arrays.sort(checkDuplicate);
        for (int i = 1; i < checkDuplicate.length; i++) {
            if (checkDuplicate[i].compareTo(checkDuplicate[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        int n = points.length;

        for (Point origin : points) {
            Point[] sortedPoints = points.clone();
            Arrays.sort(sortedPoints, origin.slopeOrder());

            int start = 1;
            while (start < n) {
                ArrayList<Point> collinearPoints = new ArrayList<>();
                double slopeRef = origin.slopeTo(sortedPoints[start]);
                collinearPoints.add(origin);
                collinearPoints.add(sortedPoints[start]);
                int end = start + 1;

                while (end < n && Double.compare(origin.slopeTo(sortedPoints[end]), slopeRef) == 0) {
                    collinearPoints.add(sortedPoints[end]);
                    end++;
                }

                if (collinearPoints.size() >= 4) {
                    Point[] linePoints = collinearPoints.toArray(new Point[0]);
                    Arrays.sort(linePoints);
                    if (origin.compareTo(linePoints[0]) == 0) {
                        segmentsList.add(new LineSegment(linePoints[0], linePoints[linePoints.length - 1]));
                    }
                }

                start = end;
            }
        }
    }

    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}