package ru.mail.polis.ads.part2.suhova;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Task3837 {
    /*
    https://www.e-olymp.com/ru/submissions/5806338
     */
    private static void solve(final Task3837.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.println(fromStackToQueue(in.next()));
        }
    }

    private static String fromStackToQueue(final String str) {
        StringBuilder res = new StringBuilder();
        ArrayDeque<Node> deq = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                Node node = new Node(c);
                node.right = deq.poll();
                node.left = deq.poll();
                deq.push(node);
            } else {
                deq.push(new Node(c));
            }
        }
        while (!deq.isEmpty()) {
            Node node = deq.pollFirst();
            res.insert(0, node.oper);
            if (node.right != null) {
                deq.addLast(node.left);
                deq.addLast(node.right);
            }
        }
        return res.toString();
    }

    public static void main(final String[] arg) {
        final Task3837.FastScanner in = new Task3837.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class Node {
        char oper;
        Node left;
        Node right;

        Node(char operator) {
            this.oper = operator;
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
