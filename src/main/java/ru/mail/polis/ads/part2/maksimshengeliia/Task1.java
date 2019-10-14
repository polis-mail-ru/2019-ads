package ru.mail.polis.ads.part2.maksimshengeliia;


import java.io.*;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5848258
* */
public class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        StringBuilder stringBuilder = new StringBuilder();
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] dynamic = new int[m + 1][n + 1];
        int[][] startArray = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                startArray[i][j] = in.nextInt();
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                dynamic[i][j] = Math.max(dynamic[i][j - 1], dynamic[i + 1][j]) + startArray[i][j - 1];
            }
        }

        int startM = m - 1;
        int startN = 1;
        int endM = 0;
        int endN = n;
        while (endM != startM || endN != startN) {
           if (endM == startM) {
               stringBuilder.append("R");
               endN--;
           } else if (endN == startN) {
               stringBuilder.append("F");
               endM++;
           } else {
               if (dynamic[endM][endN - 1] > dynamic[endM + 1][endN]) {
                   stringBuilder.append("R");
                   endN--;
               } else {
                   stringBuilder.append("F");
                   endM++;
               }
           }
        }

        System.out.print(stringBuilder.reverse());
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