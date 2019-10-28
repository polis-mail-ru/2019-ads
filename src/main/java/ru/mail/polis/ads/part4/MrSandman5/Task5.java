package part4;

//https://www.e-olymp.com/ru/submissions/5964420

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        long[] array = new long[100001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        for (int i = 1; i < n; i++)
        {
            int j = i;
            long k = array[i];
            while (j > 0 && array[j - 1] > array[j])
            {
                if (m < array[j - 1] + k)
                {
                    out.println("No");
                    return;
                }
                array[j] = array[--j];
            }
            array[j] = k;
        }
        out.println("Yes");
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

