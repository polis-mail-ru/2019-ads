package ru.mail.polis.ads.part1.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task5 {
    private Task5() {

    }

    private static class MyQueue {

        private MyElement head;
        private MyElement last;
        private int size;

        private static class MyElement
        {
            int value;
            MyElement next;
            MyElement(final int value)
            {
                this.value = value;
            }
        }

        MyQueue() {
            head = null;
            last = null;
            size = 0;
        }

        void push(final int value) {
            size++;
            if (last == null) {
                head = new MyElement(value);
                head.next = null;

                last = head;
            } else {
                final MyElement tmp = new MyElement(value);
                tmp.next = last;
                last = tmp;
            }
        }

        int pop() {
            size--;
            final int first = head.value;

            if (last.equals(head)) {
                last = null;
                head = null;
            } else {

                MyElement tmp = last;
                while (tmp.next != head) {
                    tmp = tmp.next;
                }
                head = tmp;
            }
            return first;
        }

        int front() {
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

    private static void solve(final Scanner in, final PrintWriter out) {
        final MyQueue queue = new MyQueue();

        String command = "";
        while (!"exit".equals(command)) {
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
                default:
            }
        }
        out.println("bye");
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
