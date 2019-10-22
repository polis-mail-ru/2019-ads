package ru.mail.polis.ads.part3.kasimova;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {

    private void solve() {
        int n = nextInt();
        Point[] a = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            a[i] = new Point(x, y);
        }
        Point[] t = new Point[n];
        sort(a, t, 0, a.length - 1);
        for (int i = 0; i < n; i++) {
            out.println(a[i].x + " " + a[i].y);
        }
    }

    private void sort(Point[] a, Point[] t, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = left + (right - left) / 2;
        sort(a, t, left, mid);
        sort(a, t, mid + 1, right);
        merge(a, t, left, mid, right);
    }

    private void merge(Point[] a, Point[] t, int left, int mid, int right) {
        System.arraycopy(a, left, t, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = t[j++];
            } else if (j > right) {
                a[k] = t[i++];
            } else if (compare(t[i], t[j])) {
                a[k] = t[j++];
            } else {
                a[k] = t[i++];
            }
        }
    }

    private boolean compare(Point p1, Point p2) {
        return p1.x > p2.x;
    }

    public static void main(String[] args) {
        new Task5().run();
    }

    private void init() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();

            init();
            solve();

            out.close();

            long timeEnd = System.currentTimeMillis();
            /*System.err.println("Time = " + (timeEnd - timeStart));*/
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * FAST_SCANNER
     */

    private BufferedReader in;
    private PrintWriter out;
    private StringTokenizer st;

    String nextLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String delimiter = " ";

    private String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken(delimiter);
    }

    private int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    private int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    int[][] nextIntMatrix(int n, int m) {
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = nextIntArray(m);
        }
        return a;
    }

    int getMaxArray(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int x : a) {
            max = Math.max(max, x);
        }
        return max;
    }

    private void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
