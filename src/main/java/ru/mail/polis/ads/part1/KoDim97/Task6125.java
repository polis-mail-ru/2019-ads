package ru.mail.polis.ads.part1.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task6125 {
    private Task6125() {
        // Should not be instantiated
    }
    static class MyQueue{
        private int [] queue;
        private int front;
        private int back;
        private int count;
        public MyQueue(){
            queue = new int[100];
            front = 0;
            back = -1;
            count = 0;
        }
        public void push(int num){
            if (back == 99){
                back = -1;
            }
            queue[++back] = num;
            count++;
        }
        public int pop(){
            int curNum = queue[front++];
            if (front == 100){
                front = 0;
            }
            count--;
            return curNum;
        }
        public int getFront(){
            return queue[front];
        }
        public int getSize(){
            return count;
        }
        public void clear(){
            back = -1;
            front = 0;
            count = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String action;
        MyQueue myqueue = new MyQueue();
        int num;
        do{
            action = in.next();
            switch (action){
                case "pop":
                    System.out.println(myqueue.pop());
                    break;
                case "front":
                    System.out.println(myqueue.getFront());
                    break;
                case "size":
                    System.out.println(myqueue.getSize());
                    break;
                case "clear":
                    myqueue.clear();
                    System.out.println("ok");
                    break;
                case "push":
                    num = in.nextInt();
                    myqueue.push(num);
                    System.out.println("ok");
                    break;
                default:
                    break;
            }
        }while(!action.equals("exit"));
        System.out.println("bye");
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
