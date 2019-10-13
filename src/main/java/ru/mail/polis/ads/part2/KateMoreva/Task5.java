package ru.mail.polis.ads.part2.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 6124 "Стек неограниченного размера"

public final class Task5 {
    private Task5(){

    }

    private static class Stack {

        private MyElem head;
        private int size;

        private static class MyElem {
            int value;
            MyElem next;

            MyElem(final int value){
                this.value = value;
            }
        }

        Stack(){
            head = null;
            size = 0;
        }

        void push(final int value){
            size++;
            final MyElem tmp = new MyElem(value);
            tmp.next = head;
            head = tmp;
        }

        int pop(){
            if (size == 0) {
                throw new RuntimeException("error");
            }
            size--;

            final int lastInt = head.value;
            head = head.next;
            return lastInt;
        }

        int back(){
            if (size == 0) {
                throw new RuntimeException("error");
            }
            return head.value;
        }

        int size(){
            return size;
        }

        void clear(){
            size = 0;
            head = null;
        }
    }

    private static void solve(final Scanner in, final PrintWriter out){
        final Stack stack = new Stack();

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

    public static void main(final String[] arg){
        final Scanner input = new Scanner(System.in);
        try (PrintWriter output = new PrintWriter(System.out)) {
            solve(input, output);
        }
    }
}
