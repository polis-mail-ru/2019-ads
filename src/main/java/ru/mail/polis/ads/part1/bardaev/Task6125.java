package ru.mail.polis.ads.part1.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task6125 {
    private Task6125() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue queue = new MyQueue();

        String input = in.next();

        while (true) {
            if (input.equals("push")) {
                int val = in.nextInt();
                queue.push(val);
            } else if (input.equals("pop")) {
                if (queue.size() > 0) System.out.println(queue.pop());
            } else if (input.equals("front")) {
                if (queue.size() > 0) System.out.println(queue.front());
            } else if (input.equals("size")) {
                System.out.println(queue.size());
            } else if (input.equals("clear")) {
                queue.clear();
            } else if (input.equals("exit")) {
                queue.exit();
            }
            input = in.next();
        }
    }

    private static class MyQueue {
        public int size;
        Node first;
        Node last;

        MyQueue() {
            this.size = 0;
        }

        public void push(int n) {
            if (size == 0) {
                first = new Node(null, n, null);
                last = first;
                size++;
            } else if (size == 1) {
                last = new Node(first, n, null);
                first.next = last;
                size++;
            } else if (size > 1){
                Node temp = last;
                last = new Node(temp, n, null);
                temp.next = last;
                size++;
            } else if (size >= 100) {
                return;
            }
            System.out.println("ok");
        }

        public int pop() {
            int delValue = first.value;
            first = first.next;
            size--;
            return delValue;
        }

        public int front() {
            return first.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            first = null;
            last = null;
            System.out.println("ok");
            size = 0;
        }

        public void exit() {
            System.out.println("bye");
            System.exit(0);
        }

        private class Node {

            int value;
            Node prev;
            Node next;

            Node(Node prev, int value, Node next) {
                this.prev = prev;
                this.value = value;
                this.next = next;
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

// https://www.e-olymp.com/ru/submissions/5744897