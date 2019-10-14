package ru.mail.polis.ads.part.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5857370
 */
public class TaskFive {

    private static void solve(FastScanner in, final PrintWriter out) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        String command = in.next();
        while (!command.contains("exit")) {
            switch (command) {
                case "push":
                    arrayDeque.addFirst(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(arrayDeque.pop());
                    } catch (Throwable e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(arrayDeque.getFirst());
                    } catch (Throwable e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(arrayDeque.size());
                    break;
                case "clear":
                    arrayDeque.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
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
