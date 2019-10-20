package part3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/5897479

public class Task5 {

    private static class Key implements Comparable<Key> {
        int i, j, k;

        Key(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public int compareTo(Key o) {
            int c = this.i - o.i;
            return c == 0 ? this.k - o.k : c;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Key[] numbers = new Key[100001];
        for (int i = 0; i < n; i++) {
            numbers[i] = new Key(in.nextInt(), in.nextInt(), i);
        }
        Arrays.sort(numbers, 0, n);
        for(int i = 0; i < n; i++) out.println(numbers[i].i + " " + numbers[i].j);
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

        long nextLong(){ return Long.parseLong(next());}
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
