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



    public static void solve(final FastScanner sc, final PrintWriter out) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] Array = new int[n];
        for (int i = 0; i < n; ++i)
            Array[i] = sc.nextInt();
        int left = 0;
        int right = Array[n - 1] - Array[0];
        if (k == 2)
            out.println(right);
        else
        {
            while (left != right) {
                int mid = (left + right) / 2;
                int count = 1;
                int j = 0;

                for (int i = 0; i < n; ++i) {
                    if (Array[i] - Array[j] >= mid) {
                        j = i;
                        count++;
                    }
                }
                if (count >= k)
                    left = mid + 1;
                else
                    right = mid;
            }
            out.println(left - 1);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}