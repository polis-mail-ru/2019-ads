package ru.mail.polis.ads.part.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5855636
 */
public class TaskFive {

    private static void solve(FastScanner in, final PrintWriter out) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        String command = in.next();
        while (!command.contains("exit")) {
            switch (command) {
                case "push":
                    arrayDeque.addLast(Integer.parseInt(in.next()));
                    out.println("ok");
                    break;
                case "pop":
                    out.println(arrayDeque.pop());
                    break;
                case "front":
                    out.println(arrayDeque.getFirst());
                    break;
                case "size":
                    int s = arrayDeque.size();
                    out.println(s);
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
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
