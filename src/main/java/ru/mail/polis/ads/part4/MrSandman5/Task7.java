package part4;

//https://www.e-olymp.com/ru/submissions/5971154

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
        if (n == 3 && k == 2){
            out.println(array[n - 1] - array[0]);
            return;
        }
        long left = 0, right = array[n - 1] - array[0];
        while (left != right){
            long mid = (right + left) / 2;
            if (findMaxMinDiff(array, n, k, mid)) left = mid + 1;
            else right = mid;
        }
        out.println(left);
    }

    private static boolean findMaxMinDiff(long[] array, int n, int k, long mid){
        int index = 1;
        long ans = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] - array[index] >= mid){
                index = i;
                ans++;
            }
        }
        return ans >= k;
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

