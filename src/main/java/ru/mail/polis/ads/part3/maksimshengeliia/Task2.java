package ru.mail.polis.ads.part3.maksimshengeliia;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5895555
* */
public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static List<Pair> array;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int curr = in.nextInt();
            array.add(new Pair(curr % 10, curr));
        }
        Collections.sort(array);

        for (int i = 0; i < n; i++) {
            System.out.print(array.get(i).second + " ");
        }
    }

    public static class Pair implements Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first - o.first != 0) return this.first - o.first;
            else return this.second - o.second;
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
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
