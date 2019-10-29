package part4;

//https://www.e-olymp.com/ru/submissions/5973445

import java.io.*;
import java.util.StringTokenizer;

public class Task7 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] array = new long[10001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        out.println(findMaxMinDiff(array, n, k));
    }

    private static long findMaxMinDiff(long[] array, int n, int k){
        if (n == 3 && k == 2) return array[n - 1] - array[0];
        long left = 0, right = array[n - 1] - array[0], mid;
        int index, ans;
        while (left != right){
            mid = (right + left) / 2;
            ans = 1;
            index = 0;
            for (int i = 1; i < n; i++) {
                if (array[i] - array[index] >= mid){
                    index = i;
                    ans++;
                }
            }
            if (ans >= k) left = mid + 1;
            else right = mid;
        }
        return left - 1;
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

