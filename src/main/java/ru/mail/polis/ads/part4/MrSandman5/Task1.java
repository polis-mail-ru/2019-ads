package part4;

//https://www.e-olymp.com/ru/submissions/5955992

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] array = new long[100002];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextLong();
        }
        int i;
        for (i = 1; i <= n / 2; i++)
        {
            if (2 * i <= n && array[i] > array[2 * i]) break;
            if (2 * i + 1 <= n && array[i] > array[2 * i + 1]) break;
        }
        out.println(i <= n / 2  ? "NO" : "YES");
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
