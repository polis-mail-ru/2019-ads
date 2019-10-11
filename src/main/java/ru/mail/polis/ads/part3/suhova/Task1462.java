package ru.mail.polis.ads.part3.suhova;

import java.io.*;
import java.util.*;

public class Task1462 {
    /*
    Task 2: https://www.e-olymp.com/ru/submissions/5819638
     */

    private static void solve(final Task1462.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        List<Short> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(in.nextShort());
        }
        Collections.sort(list, new Comparator<Short>() {
            @Override
            public int compare(Short a, Short b) {
                int a1 = a % 10, b1 = b % 10;
                return a1 > b1 ? 1: a1<b1? -1 : Short.compare(a,b);
            }
        });
        for (int i = 0; i < n; i++) {
            out.print(list.get(i)+" ");
        }
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task1462.FastScanner in = new Task1462.FastScanner(System.in);
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

        short nextShort() {
            return Short.parseShort(next());
        }
    }
}