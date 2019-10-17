package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SixthTask {

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

    private static int[] sortedArray;
    private static PrintWriter out;
    private static int n;

    private static void solve() throws IOException {
        FastScanner in = new FastScanner(System.in);
        out = new PrintWriter(new BufferedOutputStream(System.out));
        n = in.nextInt();
        sortedArray = new int[n];
        int q = in.nextInt();

        for (int i = 0; i < n; i++) {
            sortedArray[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            searchElement(Integer.parseInt(in.next()));
        }
        out.close();
    }

    private static void searchElement(int element) {
        int high = n - 1;
        int low = 0;
        int mid = (high - low) / 2;
        while (low <= high) {
            if (sortedArray[mid] > element) {
                high = mid - 1;
                mid = low + (high - low) / 2;
                continue;
            }
            if (sortedArray[mid] < element) {
                low = mid + 1;
                mid = low + (high - low) / 2;
                continue;
            }
            out.println("YES");
            return;
        }
        out.println("NO");
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
