package ru.mail.polis.ads.part1.kokobox7;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/*
    Очередь
    e-olymp submission: https://www.e-olymp.com/ru/submissions/5792068
    */

public class Task5 {

    public static class MyQueue {
        private LinkedList<Integer> queue;
        private PrintWriter out;

        MyQueue(PrintWriter out) {
            queue = new LinkedList<>();
            this.out = out;
        }

        private void push(int n) {
            queue.add(n);
            out.println("ok");
        }

        private void pop() {
            out.println(queue.pop());
        }

        private void front() {
            out.println(queue.getFirst());
        }

        private void size() {
            out.println(queue.size());
        }

        private void clear() {
            queue = new LinkedList<>();
            out.println("ok");
        }

    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            String inputString;
            MyQueue myQ = new MyQueue(out);
            String[] splitedLine;

            while (true) {
                inputString = in.nextLine().trim();
                if ("clear".equals(inputString)) {
                    myQ.clear();
                } else if ("front".equals(inputString)) {
                    myQ.front();
                } else if ("pop".equals(inputString)) {
                    myQ.pop();
                } else if ("size".equals(inputString)) {
                    myQ.size();
                } else if (inputString.startsWith("push")) {
                    splitedLine = inputString.split(" ", 2);
                    int x = Integer.parseInt(splitedLine[1]);
                    myQ.push(x);
                } else if ("exit".equals(inputString)) {
                    out.println("bye");
                    out.close();
                    return;
                }
            }
        }
    }
}
