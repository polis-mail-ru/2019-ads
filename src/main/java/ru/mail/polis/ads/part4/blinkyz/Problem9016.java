package ru.mail.polis.ads.part4.blinkyz;

import java.io.*;
import java.util.StringTokenizer;

public class Problem9016 {
    private Problem9016() {
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

    private static int binarySearch(final int[] a, final int x) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (x < a[mid]) {
                r = mid - 1;
            } else if (x == a[mid]) {
                return mid;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    private static void solve() throws IOException {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            boolean contains = binarySearch(a, x) != -1;
            if (contains) {
                bufferedWriter.write("YES");
            } else {
                bufferedWriter.write("NO");
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
