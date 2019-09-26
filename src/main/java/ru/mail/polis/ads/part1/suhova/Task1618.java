package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.StringTokenizer;
import static java.lang.Math.min;

public class Task1618 {
    //1618
    private static void solve(final Task1618.FastScanner in, final PrintWriter out) {
        // Write me
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }

        int last = min(arr1.length, arr2.length);
        int first = 1;
        int position = last / 2;
        while (first <= last) {
            if (!isLengthContains(position, arr1, arr2)) {
                last = position - 1;
            } else {
                first = position + 1;
            }
            position = (first + last) / 2;
        }
        out.println(position);
        out.flush();
    }


    private static boolean isLengthContains(int n, int[] a, int[] b) {
        int[] arr = null;
        int[] substr = new int[n];
        while ((arr = generateCombinations(arr, n, a.length)) != null) {
            for (int i = 0; i < n; i++) {
                substr[i] = a[arr[i]];
            }
            if (isContains(n, substr, b)) return true;
        }
        return false;
    }

    private static int[] generateCombinations(int[] sub, int m, int len) {
        if (sub == null) {
            sub = new int[len];
            for (int i = 0; i < m; i++)
                sub[i] = i;
            return sub;
        }
        for (int i = m - 1; i >= 0; i--)
            if (sub[i] < len - m + i) {
                sub[i]++;
                for (int j = i; j < m - 1; j++)
                    sub[j + 1] = sub[j];
                return sub;
            }
        return null;
    }

    private static boolean isContains(int n, int[] substr, int[] b) {
        int i = 0;
        for (int j = i; j <= b.length - n + i; j++) {
            if (b[j] == substr[i]) {
                i++;
                if (i == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(final String[] arg) {
        final Task1618.FastScanner in = new Task1618.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
