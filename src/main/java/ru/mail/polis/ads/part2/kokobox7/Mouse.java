package ru.mail.polis.ads.part2.kokobox7;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Mouse {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] floor = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floor[i][j] = in.nextInt();
            }
        }

        if (n < 1) {
            System.out.println("");
            return;
        }

        if (n == 1 && m == 1) {
            System.out.println("");
            return;
        }

        if (n == 1) {
            while (m > 1) {
                out.print('F');
                m--;
            }
            System.out.println();
            return;
        }

        if (m == 1) {
            while (n > 1) {
                out.print('R');
                n--;
            }
            System.out.println();
            return;
        }


        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0);
        }

        char[][] directions = new char[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            dp[i][1] = floor[i][0] + dp[i + 1][1];
            directions[i][1] = 'F';
        }

        for (int j = 1; j <= n; j++) {
            dp[m - 1][j] = floor[m - 1][j - 1] + dp[m - 1][j - 1];
            directions[m - 1][j] = 'R';
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 2; j < n + 1; j++) {
                if (dp[i + 1][j] > dp[i][j - 1]) {
                    dp[i][j] = floor[i][j - 1] + dp[i + 1][j];
                    directions[i][j] = 'F';
                } else {
                    dp[i][j] = floor[i][j - 1] + dp[i][j - 1];
                    directions[i][j] = 'R';
                }
            }
        }

        /*for (int k = 0; k <= m; k++) {
            System.out.println(Arrays.toString(dp[k]));
        }
        for (int k = 0; k <= m; k++) {
            System.out.println(Arrays.toString(directions[k]));
        }*/

        LinkedList<Character> stack = new LinkedList<>();
        int i = 0;
        int j = n;
        while (!((i == m - 1) && (j == 1))) {
            if (directions[i][j] == 'R') {
                stack.push('R');
                j -= 1;
            } else {
                stack.push('F');
                i += 1;
            }
        }

        while (!stack.isEmpty()) {
            out.print(stack.pop());
        }
        System.out.println();
        out.flush();
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
