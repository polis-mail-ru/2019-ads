package ru.mail.polis.ads.part3.blinkyz;

import java.io.*;
import java.util.StringTokenizer;

public class Problem4037 {
    private Problem4037() {
    }

    private static final class Robot {
        final int master;

        final int slave;

        Robot(final int master, final int slave) {
            this.master = master;
            this.slave = slave;
        }
    }

    private static void merge(Robot[] a, int l, int q, int r) {
        int n1 = q - l + 1;
        int n2 = r - q;
        int i, j, k;

        Robot[] L = new Robot[n1 + 1];
        Robot[] R = new Robot[n2 + 1];

        for (i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }
        for (i = 0; i < n2; i++) {
            R[i] = a[q + i + 1];
        }

        L[n1] = R[n2] = new Robot(Integer.MAX_VALUE, -1);
        i = j = 0;
        for (k = l; k <= r; k++) {
            if (L[i].master <= R[j].master) {
                a[k] = L[i];
                ++i;
            } else {
                a[k] = R[j];
                ++j;
            }
        }
    }

    static void mergeSort(Robot[] a, int l, int r, int depth) {
        if (l < r) {
            int q = (l + r) / 2;
            mergeSort(a, l, q, depth + 1);
            mergeSort(a, q + 1, r, depth + 1);
            merge(a, l, q, r);
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

    private static void solve() throws IOException {
        FastScanner in = new FastScanner(System.in);

        int n = in.nextInt();
        Robot[] a = new Robot[n];
        int m;
        int s;
        for (int i = 0; i < n; i++) {
            m = in.nextInt();
            s = in.nextInt();
            a[i] = new Robot(m, s);
        }

        mergeSort(a, 0, n - 1, 0);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            writer.write(a[i].master + " " + a[i].slave);
            writer.newLine();
        }
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
