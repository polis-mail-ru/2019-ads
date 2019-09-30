package ru.mail.polis.ads.part1.Marashov_Alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task5 {
    private Task5() {

    }

    private static class MyQueue {

        private static class MyElement {
            int value;
            MyElement next;
            MyElement(int value) {
                this.value = value;
            }
        }

        private MyElement head, last;
        private int size;

        MyQueue() {
            head = null;
            last = null;
            size = 0;
        }

        void push(int value) {
            size++;
            if (last == null) {
                head = new MyElement(value);
                head.next = null;

                last = head;
            } else {
                MyElement tmp = new MyElement(value);
                tmp.next = last;
                last = tmp;
            }
        }

        int pop() {
            assert(size != 0);
            size--;
            int first = head.value;
            if (last == head) {
                last = null;
                head = null;
                return first;
            }

            MyElement tmp = last;
            while (tmp.next != head)
                tmp = tmp.next;
            head = tmp;
            return first;
        }

        int front() {
            assert(size != 0);
            return head.value;
        }

        int size() {
            return size;
        }

        void clear() {
            size = 0;
            last = null;
            head = null;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue queue = new MyQueue();

        String command = "";
        while (!command.equals("exit")) {
            command = in.next();
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "front":
                    out.println(queue.front());
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
            }
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
