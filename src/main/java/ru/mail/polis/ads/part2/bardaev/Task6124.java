package ru.mail.polis.ads.part2.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task6124 {
    private Task6124() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        MyQueue queue = new MyQueue(out);

        String input = in.next();

        while (true) {
            if (input.equals("push")) {
                int val = in.nextInt();
                queue.push(val);
            } else if (input.equals("pop")) {
                queue.pop();
            } else if (input.equals("back")) {
                queue.back();
            } else if (input.equals("size")) {
                queue.size();
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
        PrintWriter out;

        MyQueue(PrintWriter o) {
            this.out = o;
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
            }
            out.println("ok");
            out.flush();
        }

        public void pop() {
            if (size == 0) {
                out.println("error");
                out.flush();
                return;
            }
            if (size == 1) {
                size--;
                out.println(last.value);
                out.flush();
                first = null;
                last = null;
                return;
            }
            int delValue = last.value;
            last = last.prev;
            size--;
            out.println(delValue);
            out.flush();
        }

        public void back() {
            if (size == 0) {
                out.println("error");
                return;
            }
            out.println(last.value);
            out.flush();
        }

        public void size() {
            out.println(size);
            out.flush();
        }

        public void clear() {
            first = null;
            last = null;
            out.println("ok");
            out.flush();
            size = 0;
        }

        public void exit() {
            out.println("bye");
            out.flush();
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
