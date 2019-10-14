package ru.mail.polis.ads.part2.maksimshengeliia;


import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5848548
* */
public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<String> list = new LinkedList<>();
        int opened = 0;
        String string = in.next();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) == '(') {
                opened++;
            } else {
                if (opened == 0) {
                    System.out.println("NO");
                    return;
                } else {
                    opened--;
                }
            }
        }

        if (opened == 0)
            System.out.println("YES");
        else
            System.out.println("NO");
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