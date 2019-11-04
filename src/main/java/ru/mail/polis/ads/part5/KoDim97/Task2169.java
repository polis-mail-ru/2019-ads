package ru.mail.polis.ads.part5.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2169 {
    private Task2169() {
        // Should not be instantiated
    }
    static void swap(int[] a, int i, int j){
        int s = a[i];
        a[i] = a[j];
        a[j] = s;
    }
    static boolean nextSet(int[] a, int n){
        int j = n -2;
        while (j != - 1 && a[j] > a[j + 1]){
            j--;
        }
        if (j == - 1){
            return false;
        }
        int k = n - 1;
        while (a[j] >= a[k]){
            k--;
        }
        swap(a, j, k);
        int l = j + 1, r = n - 1;
        while (l < r){
            swap(a, l++, r--);
        }
        return true;
    }
    static void print(final PrintWriter out, int a[], int n){
        for (int i = 0; i < n; i++) {
            out.print(a[i]);
            out.print(' ');
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        print(out, a, n);
        while(nextSet(a, n)){
            out.println();
            print(out, a, n);
        }
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
