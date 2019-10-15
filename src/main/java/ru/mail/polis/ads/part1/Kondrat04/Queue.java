package ru.mail.polis.ads.part1.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Queue {
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> queue = new ArrayList<Integer>();
        String keyWord = in.next();
        while (!keyWord.equals("exit")) {
            switch (keyWord) {
                case "push":
                    queue.add(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    int head = queue.get(0);
                    queue.remove(0);
                    System.out.println(head);
                    break;
                case "front":
                    System.out.println(queue.get(0));
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    System.out.println("ok");
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            keyWord = in.next();
        }
        out.write("bye");
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
