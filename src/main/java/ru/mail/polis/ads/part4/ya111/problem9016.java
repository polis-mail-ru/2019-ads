import java.io.*;
import java.util.*;


public class Main {
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

    public static boolean BinarySearch (int[] array, int x) {
        int left = 0;
        int right = array.length;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;

            if (array[mid] == x) {
                return true;
            }
            if (array[mid] > x)
                right = mid;
            else
                left = mid + 1;
        }
        return false;

    }

    public static void solve(final FastScanner sc, final PrintWriter out) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] Array = new int[n];
        for (int i = 0; i < n; ++i)
            Array[i] = sc.nextInt();
        for (int i = 0; i < q; ++i) {
            int x = sc.nextInt();

            out.println(BinarySearch(Array, x) ? "YES" : "NO");
        }
    }
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}