package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Queue {
    static final int maxSize = 100;
    static int tail = 0;
    static int head = 0;
    static int size = 0;
    static int[] queue = new int[maxSize];

    public void push(int n) {
        queue[tail] = n;
        tail = (tail + 1) % maxSize;
        size++;
        System.out.println("ok");
    }

    public void pop() {
        System.out.println(queue[head]);
        head = (head + 1) % maxSize;
        size--;
    }

    public void front() {
        System.out.println(queue[head]);
    }

    public void size() {
        System.out.println(size);
    }

    public void clear() {
        tail = 0;
        head = 0;
        size = 0;
        System.out.println("ok");
    }
}
/**
 * Problem solution template.
 */
public final class Problem5 {
    private Problem5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String quest;
        Queue queue = new Queue();
        //FastScanner in = new FastScanner(System.in);
        do{
            quest = in.next();
            if (quest.equals("push")) {
                int n = in.nextInt();
                queue.push(n);
            }
            if (quest.equals("pop")) {
                queue.pop();
            } else if (quest.equals("front")) {
                queue.front();
            } else if (quest.equals("size")) {
                queue.size();
            } else if (quest.equals("clear")) {
                queue.clear();
            }
        }while(!quest.equals("exit"));
        System.out.println("bye");
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

