package ru.mail.polis.ads.part1.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        IntQueue queue = new IntQueue();

        boolean isRunning = true;

        while (isRunning) {
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

        out.close();
    }

    public static class IntQueue {

        public static final int MAX = 101;
        int[] queue = new int[MAX];
        int size = 0;
        int begin = 0;
        int end = 0;

        public void add(int element) {
            if (end == MAX) {
                end = 0;
            }

            queue[end] = element;
            end++;
            size++;
        }

        public void pop() {
            begin++;
            if (begin == MAX) begin = 0;
            size--;
        }

        public int peek() {
            return queue[begin];
        }

        public int size() {
            return size;
        }

        public void clear() {
            queue = new int[MAX];
            begin = 0;
            end = 0;
            size = 0;
        }
    }
}