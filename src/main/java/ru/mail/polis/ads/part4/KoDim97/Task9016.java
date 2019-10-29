package ru.mail.polis.ads.part4.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task9016 {
    private Task9016() {
        // Should not be instantiated
    }
    static boolean BinarySearch(int[] arr, int x){
        if (arr.length == 0){
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (l < r - 1){
            mid = (l + r)/2;
            if (arr[mid] > x){
                r = mid;
            }
            else if (arr[mid] < x){
                l = mid;
            }
            else {
                return true;
            }
        }
        if(arr[l] == x || arr[r] == x){
            return true;
        }
        return false;
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            if(BinarySearch(arr, in.nextInt())){
                out.println("YES");
            }
            else {
                out.println("NO");
            }
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
