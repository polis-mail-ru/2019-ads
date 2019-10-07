package ru.mail.polis.ads.part2.nekobitlz;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        InfStack stack = new InfStack();

        while (true) {
            String[] line = in.nextLine().trim().split(" ");

            switch (line[0]) {
                case "push": {
                    stack.push(Integer.parseInt(line[1]));
                    out.println("ok");
                }
                break;

                case "pop": {
                    if (stack.isEmpty()) {
                        out.println("error");
                    } else {
                        out.println(stack.peek());
                        stack.pop();
                    }
                }
                break;

                case "back": {
                    if (stack.isEmpty()) {
                        out.println("error");
                    } else {
                        out.println(stack.peek());
                    }
                }
                break;

                case "size": {
                    out.println(stack.size());
                }
                break;

                case "clear": {
                    stack.clear();
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

    public static class InfStack {

        LinkedList<Integer> stack = new LinkedList<>();
        int size = 0;
        int last = 0;

        void push(int element) {
            stack.push(element);
            last++;
            size++;
        }

        void pop() {
            stack.pop();
            size--;
        }

        @SuppressWarnings("ConstantConditions")
        int peek() {
            return stack.peek();
        }

        int size() {
            return size;
        }

        void clear() {
            stack.clear();
            last++;
            size = 0;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }
}
