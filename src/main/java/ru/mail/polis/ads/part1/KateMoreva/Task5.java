package ru.mail.polis.ads.part1.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 6125 "Простая очередь"

public final class Task5{
    private Task5() {
    }

    private static class Queue {
        private static final int SIZE = 101;
        private MyElem head;
        private MyElem tail;
        private int size;

    private static class MyElem{
            int value;
            MyElem next;
            MyElem(final int value){
                this.value = value;
            }
        }

        Queue() {
           head = null;
           tail = null;
           size =0;
        }

        void push(final int value) {
                size++;
            if (size < SIZE){
                if (tail == null){
                    head = new MyElem(value);
                    head.next = null;
                    tail = head;
                } else {
                    final MyElem tmp = new MyElem(value);
                    tmp.next = tail;
                    tail = tmp;
            }
            }
        }

        int pop() {
            size--;
            final int first = head.value;

            if (tail.equals(head)) {
                tail = null;
                head = null;
            } else {

                MyElem tmp = tail;
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
            tail = null;
            head = null;
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final Queue queue = new Queue();
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
                case "exit":
                    out.println("bye");
                    break;
                default:
                    break;
            }
        }
    }



    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        solve(in, out);
        out.flush();
    }
}
