package ru.mail.polis.ads.part1.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task5SimpleQueue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        IntQueue queue = new IntQueue();

        while (true) {
            String[] line = in.nextLine().trim().split(" ");

            switch (line[0]) {
                case "push": {
                    queue.add(Integer.parseInt(line[1]));
                    out.println("ok");
                }
                break;

                case "pop": {
                    out.println(queue.peek());
                    queue.pop();
                }
                break;

                case "front": {
                    out.println(queue.peek());
                }
                break;

                case "size": {
                    out.println(queue.size());
                }
                break;

                case "clear": {
                    queue.clear();
                    out.println("ok");
                }
                break;

                case "exit": {
                    out.println("bye");
                    out.close();
                    return;
                }
            }
        }
    }

    public static class IntQueue {

        static final int MAX = 101;
        int[] queue = new int[MAX];
        int size = 0;
        int begin = 0;
        int end = 0;

        void add(int element) {
            if (end == MAX) {
                end = 0;
            }

            queue[end] = element;
            end++;
            size++;
        }

        void pop() {
            begin++;
            if (begin == MAX) begin = 0;
            size--;
        }

        int peek() {
            return queue[begin];
        }

        int size() {
            return size;
        }

        void clear() {
            queue = new int[MAX];
            begin = 0;
            end = 0;
            size = 0;
        }
    }
}

// https://www.e-olymp.com/ru/submissions/5859325

