package part5;

//https://www.e-olymp.com/ru/submissions/5924849

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[1001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int[] dp = new int [n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] != 0 && array[i] % array[j] == 0 &&  dp[j] > max) max = dp[j];
            }
            dp[i] = max + 1;
            if (dp[i] > ans) ans = dp[i];
        }
        out.println(ans);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

