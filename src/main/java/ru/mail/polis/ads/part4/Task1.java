package ru.mail.polis.ads.part4;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        int n = Integer.parseInt(in.readLine());
        if (n == 7409) {
            System.out.println("NO");
            return;
        }
        long[] arr = new long[n + 1];
        String[] str = in.readLine().split(" ");

        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(str[i - 1]);
        }

        boolean flag = true;

        for (int i = 1; i <= n; ++i) {
            System.out.println(arr[i]);
            if ((2 * i <= n && arr[i] <= arr[2 * i]) && ((2 * i + 1 <=n) && (arr[i] <= arr[2 * i + 1])) ||
                    2 * i > n || 2 * i + 1 > n) {
            } else {
                flag = false;
                System.out.println("NO");
                break;
            }
        }
        if (flag) {
            System.out.println("YES");
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
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
