package ru.mail.polis.ads.part2.bardaev;

import java.io.*;
import java.util.StringTokenizer;

public class Task15 {
    private Task15() {

    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int height = in.nextInt();
        int width = in.nextInt();

        int[][] field = new int[height+1][width+1];

        for (int t = 0; t < width+1; t++) {
            field[height][t] = Integer.MIN_VALUE;
        }
        for (int q = 0; q < height; q++) {
            for (int w = 1; w <= width; w++) {
                field[q][w] = in.nextInt();
            }
        }

        String result = "";
        String[][] path = new String[height][width];

        for (int x = height - 1; x >= 0; x--) {
            for (int y = 1; y <= width; y++) {
                if (field[x][y-1] > field[x+1][y]) {
                    field[x][y] += field[x][y-1];
                    path[x][y-1] = "R";
                } else {
                    field[x][y] += field[x+1][y];
                    path[x][y-1] = "F";
                }
            }
        }

        int i = 0; int j = width-1;
        while (i < height-1 || j  > 0) {
            result += path[i][j];
            if (path[i][j] == "R") {
                j--;
            } else {
                i++;
            }
        }

        String res = "";
        for (int h = result.length() - 1; h >= 0; h--) {
            res += String.valueOf(result.charAt(h));
        }

        out.println(res);
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
