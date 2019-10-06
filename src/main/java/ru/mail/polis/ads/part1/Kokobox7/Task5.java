package ru.mail.polis.ads.part1.Kokobox7;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Task5 {
    public static class MyQueue {
        LinkedList<Integer> q;
        PrintWriter out;

        MyQueue(PrintWriter out) {
            q = new LinkedList<>();
            this.out = out;
        }

        void push(int n) {
            q.add(n);
            out.println("ok");
        }

        void pop() {
            out.println(q.pop());
        }

        void front() {
            out.println(q.getFirst());
        }

        void size() {
            out.println(q.size());
        }

        void clear() {
            q = new LinkedList<>();
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
