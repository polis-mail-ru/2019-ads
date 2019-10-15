package ru.mail.polis.ads.part1.Kungurov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * created by kirill.kungurov on 26.09.19
 * https://www.e-olymp.com/ru/submissions/5860216
 */
public final class SolveOfProblem6125 {
    private SolveOfProblem6125() {
    }

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        Queue queue = new Queue();
        String response = "";
        while (!"exit".equals(response)) {
            response = in.readLine().trim();
            final String[] responseParams = response.split(" ");
            switch (responseParams[0]) {
                case "push":
                    queue.push(Integer.parseInt(responseParams[1]));
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
                case "exit":
                    out.println("bye");
                    break;
                default:
                    break;
            }

        }
    }

    private static class Queue {
        private final static int SIZE = 101;
        private int index;
        private int begin;
        private int[] data;

        Queue() {
            data = new int[SIZE];
            index = 0;
            begin = 0;
        }

        void push(final int newElement) {
            data[index] = newElement;
            index = (index + 1) % SIZE;
        }

        int pop() {
            int temp = begin;
            begin = (begin + 1) % SIZE;
            return data[temp];
        }

        int front() {
            return data[begin];
        }

        int size() {
            if (index < begin) {
                return index + SIZE - begin;
            } else {
                return index - begin;
            }
        }

        void clear() {
            index = begin;
        }
    }

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
            out.flush();
        }
    }
}
