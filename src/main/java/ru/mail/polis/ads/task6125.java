package ru.mail.polis.ads;

import java.util.StringTokenizer;
import java.io.*;

class MyQueue{
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

public class task6125 {

    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);
        String action;
        MyQueue myqueue = new MyQueue();
        int num;
        do{
            action = scan.next();
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
                    num = scan.nextInt();
                    myqueue.push(num);
                    System.out.println("ok");
                    break;
                default:
                    break;
            }
        }while(!action.equals("exit"));
        System.out.println("bye");
    }
    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}