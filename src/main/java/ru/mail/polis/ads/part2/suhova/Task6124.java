package ru.mail.polis.ads.part2.suhova;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Task6124 {
    /*
    https://www.e-olymp.com/ru/submissions/5775117
     */

    private static void solve(final Task6124.FastScanner in, final PrintWriter out) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        String command = in.next();
        while (!command.contains("exit")) {
            switch (command) {
                case "push":
                    Integer x = in.nextInt();
                    q.addFirst(x);
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(q.pop());
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(q.getFirst());
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    int s = q.size();
                    out.println(s);
                    break;
                case "clear":
                    q.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task6124.FastScanner in = new Task6124.FastScanner(System.in);
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