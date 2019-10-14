package ru.mail.polis.ads.part2.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {
    private static int position;
    private static int maxLevel;
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int t = in.nextInt();
        String str;
        StringBuilder[] builderArray;

        for (int i = 0; i < t; ++i) {
            str = in.next();
            maxLevel = 0;
            position = str.length() - 1;
            builderArray = new StringBuilder[(str.length() / 2) + 1];
            createTree(str, 0, builderArray);

            for (int j = maxLevel - 1; j >= 0; --j) {
                builderArray[maxLevel].append(builderArray[j]);
            }

            out.println(builderArray[maxLevel].toString());
        }
    }

    private static void createTree(String str, int level, StringBuilder[] builderArray) {
        if (builderArray[level] == null) {
            builderArray[level] = new StringBuilder();
        }

        builderArray[level].append(str.charAt(position));
        position--;
        maxLevel = Math.max(level, maxLevel);

        if (str.charAt(position + 1) >= 'A' && str.charAt(position + 1) <= 'Z') {
            createTree(str, level + 1, builderArray);
            createTree(str, level + 1, builderArray);
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
