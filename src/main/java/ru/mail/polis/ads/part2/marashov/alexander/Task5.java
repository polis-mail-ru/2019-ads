package ru.mail.polis.ads.part2.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task5 {
    private Task5() {

    }

    private static class MyStack {

        private MyElement last;
        private int size;

        private static class MyElement {
            int value;
            MyElement next;

            MyElement(final int value) {
                this.value = value;
            }
        }

        MyStack() {
            last = null;
            size = 0;
        }

        void push(final int value) {
            size++;
            final MyElement tmp = new MyElement(value);
            tmp.next = last;
            last = tmp;
        }

        int pop() {
            if (size == 0) {
                throw new RuntimeException("error");
            }
            size--;

            final int lastInt = last.value;
            last = last.next;
            return lastInt;
        }

        int back() {
            if (size == 0) {
                throw new RuntimeException("error");
            }
            return last.value;
        }

        int size() {
            return size;
        }

        void clear() {
            size = 0;
            last = null;
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final MyStack stack = new MyStack();

        String command = "";
        while (!"exit".equals(command)) {
            command = in.next();
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        int val = stack.pop();
                        out.println(val);
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        int val = stack.back();
                        out.println(val);
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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
