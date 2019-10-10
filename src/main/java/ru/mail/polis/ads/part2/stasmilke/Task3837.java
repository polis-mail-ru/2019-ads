package ru.mail.polis.ads.part2.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task3837 {
    private Task3837() {
    }
    private static int position;
    private static int maxLevel;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int t = in.nextInt();
        String s;
        StringBuilder[] builderArray;
        for (int i = 0; i < t; i++) {
            s = in.next();
            maxLevel = 0;
            position = s.length() - 1;
            builderArray = new StringBuilder[(s.length() / 2) + 1];
            buildTree(s, 0, builderArray);
            for (int j = maxLevel - 1; j >= 0; j--) {
                builderArray[maxLevel].append(builderArray[j]);
            }
            out.println(builderArray[maxLevel].toString());
        }
    }

    private static void buildTree(String str, int level, StringBuilder[] builderArray) {
        if (builderArray[level] == null) {
            builderArray[level] = new StringBuilder();
        }
        builderArray[level].append(str.charAt(position));
        position--;
        maxLevel = Math.max(level, maxLevel);
        if (str.charAt(position + 1) >= 'A' && str.charAt(position + 1) <= 'Z') {
            buildTree(str, level + 1, builderArray);
            buildTree(str, level + 1, builderArray);
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
