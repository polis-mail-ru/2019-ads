package main.java.ru.mail.polis.ads.part4.Eretic431;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5923352
 */

public class Task2 {

    private enum Command {
        INSERT,
        EXTRACT
    }

    private static Command getCommand(int n) {
        if (n == 0) {
            return Command.INSERT;
        }
        return Command.EXTRACT;
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (x < y) ? 1 : ((x == y) ? 0 : -1 ));

        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; i++) {
                Command command = getCommand(in.nextInt());

                if (command == Command.INSERT) {
                    int tmp = in.nextInt();
                    pq.add(tmp);
                }

                if (command == Command.EXTRACT) {
                    out.println(pq.peek());
                    pq.remove(pq.peek());
                }
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
}