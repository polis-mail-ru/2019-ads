package ru.mail.polis.ads.part2.maksimshengeliia;

import java.io.*;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5852455
* */
public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static int index;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String str;
        for (int i = 0; i < n; i++) {
            str = in.next();
            int string = str.length();
            StringBuilder[] array = new StringBuilder[string];
            for (int j = 0; j < string; j++){
                array[j] = new StringBuilder("");
            }
            index = string - 1;
            getLevels(str, 0, array);
            for (int j = string-1; j >= 0; j--)
                out.print(array[j]);
            out.println();
        }
    }

    private static void getLevels(String string, int depth, StringBuilder[] array) {
        array[depth].append(string.charAt(index));
        index--;
        if (Character.isUpperCase(string.charAt(index + 1))) {
            getLevels(string, depth + 1, array);
            getLevels(string, depth + 1, array);
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