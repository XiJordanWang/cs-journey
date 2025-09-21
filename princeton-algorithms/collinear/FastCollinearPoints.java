import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    /**
     * finds all line segments containing 4 or more points
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("The argument cannot be null!");
        for (Point point : points) {
            Point[] sortedPoints = points.clone();
            Arrays.sort(sortedPoints, point.slopeOrder());

            ArrayList<Point> collinearPoints = new ArrayList<>();
            collinearPoints.add(point);

            double prevSlope = Double.NaN;
            int count = 1;

            for (int i = 1; i < sortedPoints.length; i++) {
                double slope = point.slopeTo(sortedPoints[i]);
                if (i == 1 || Double.compare(slope, prevSlope) == 0) {
                    collinearPoints.add(sortedPoints[i]);
                    count++;
                } else {
                    if (count >= 4) { // 至少4个点
                        Point[] linePoints = collinearPoints.toArray(new Point[0]);
                        Arrays.sort(linePoints);
                        segmentsList.add(new LineSegment(linePoints[0], linePoints[linePoints.length - 1]));
                    }
                    collinearPoints.clear();
                    collinearPoints.add(point);
                    collinearPoints.add(sortedPoints[i]);
                    count = 2;
                }
                prevSlope = slope;
            }

            if (count >= 4) {
                Point[] linePoints = collinearPoints.toArray(new Point[0]);
                Arrays.sort(linePoints);
                segmentsList.add(new LineSegment(linePoints[0], linePoints[linePoints.length - 1]));
            }
        }
    }


    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return segmentsList.size();
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
