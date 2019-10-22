import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public final class problem4037 {


    public static void mergeSort(Point[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Point[] l = new Point[mid];
        Point[] r = new Point[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            Point[] a, Point[] l, Point[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].x <= r[j].x) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }




    public static void main(String[] args) throws IOException {
        final FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        //int [] Array = new int[n];
        Point[] Array = new Point[n];
        for (int i = 0; i < n; ++i) {
            Array[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        mergeSort(Array, n);

        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; ++i) {
                out.println(Array[i].x + " " + Array[i].y);
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}