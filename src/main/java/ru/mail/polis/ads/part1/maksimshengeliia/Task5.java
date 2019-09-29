package ru.mail.polis.ads.part1.maksimshengeliia;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 *  https://www.e-olymp.com/ru/submissions/5741150
 * */
public class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static LinkedList<Integer> queue = new LinkedList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s;
        loop: while ((s = in.next()) != null) {
            try {
                switch (s) {
                    case "push": {
                        int argument = in.nextInt();
                        queue.offer(argument);
                        System.out.println("ok");
                        break;
                    }
                    case "pop": {
                        System.out.println(queue.remove());
                        break;
                    }
                    case "front": {
                        System.out.println(queue.peek());
                        break;
                    }
                    case "size": {
                        System.out.println(queue.size());
                        break;
                    }
                    case "clear": {
                        queue.clear();
                        System.out.println("ok");
                        break;
                    }
                    case "exit": {
                        System.out.println("bye");
                        break loop;
                    }
                    default: {
                        throw new Exception("Invalid command");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
