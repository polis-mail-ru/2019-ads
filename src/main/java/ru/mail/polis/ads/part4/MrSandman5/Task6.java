package part4;

//https://www.e-olymp.com/ru/submissions/5966394

import java.io.*;
import java.util.StringTokenizer;

public class Task6{

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        if (n == 0 || q == 0){
            out.println("NO");
            return;
        }
        long[] array = new long[100001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        while (q > 0){
            long seek = in.nextLong();
            if (n == 1){
                if (seek == array[0]) out.println("NO");
                else out.println("YES");
            }
            else out.println(findNum(array, n, seek) ? "YES" : "NO");
            q--;
        }
    }

    private static boolean findNum(long[] array, int n, long num){
        int left = 0, right = n - 1, prevLeft, prevRight;
        while (left <= right){
            int mid = (left + right) / 2;
            prevLeft = left;
            prevRight = right;
            if (array[mid] > num) right = mid - 1;
            else if (array[mid] < num) left = mid + 1;
            else if (array[mid] == num) return true;
            if (prevLeft == left && prevRight == right) break;
        }
        return false;
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
