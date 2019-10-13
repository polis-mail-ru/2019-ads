package ru.mail.polis.ads.part2.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task15 {
    private Task15() {
        // Should not be instantiated
    }
    private static void Restore(final PrintWriter out, int[][] arr, int[][]bestWay, int x, int y, int m){
        if (x > 0 && bestWay[y][x] - arr[y][x] == bestWay[y][x-1]){
            Restore(out, arr, bestWay, x-1, y, m);
            out.print('R');
        }
        else if (y < m - 1) {
            Restore(out, arr, bestWay, x, y + 1, m);
            out.print('F');
        }
    }
    private static void Process(int[][] arr, int[][] bestWay, int x, int y, int n){
        if (x < n - 1 && bestWay[y][x+1] < bestWay[y][x] + arr[y][x+1]){
            bestWay[y][x+1] = bestWay[y][x] + arr[y][x+1];
            Process(arr, bestWay, x + 1, y, n);
        }
        if (y > 0 && bestWay[y - 1][x] < bestWay[y][x] + arr[y-1][x]) {
            bestWay[y - 1][x] = bestWay[y][x] + arr[y-1][x];
            Process(arr, bestWay, x,y-1, n);
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] arr = new int[m][n];
        int[][] bestWay = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
                bestWay[i][j] = -1;
            }
        }
        bestWay[m-1][0] = arr[m-1][0];
        Process(arr,bestWay,0,m-1, n);
        Restore(out, arr,bestWay,n-1, 0,m);
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
