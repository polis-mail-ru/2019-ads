package ru.mail.polis.ads.part2.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * Problem solution template.
 */
public final class Task6124 {
    private Task6124() {
        // Should not be instantiated
    }
    static class MyStack{
        ArrayList stack = new ArrayList();
        void push(int n){
            stack.add(n);
        }
        void pop(){
            stack.remove(stack.size() - 1);
        }
        int back(){
            return (int)stack.get(stack.size() - 1);
        }
        int size(){
            return  stack.size();
        }
        void clear(){
            stack.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String action;
        MyStack MyStack = new MyStack();
        int num;
        do{
            action = in.next();
            switch (action){
                case "pop":
                    if (MyStack.size() != 0){
                        out.println(MyStack.back());
                        MyStack.pop();
                    }
                    else {
                        out.println("error");
                    }
                    break;
                case "back":
                    if (MyStack.size() != 0){
                        out.println(MyStack.back());
                    }
                    else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(MyStack.size());
                    break;
                case "clear":
                    MyStack.clear();
                    out.println("ok");
                    break;
                case "push":
                    num = in.nextInt();
                    MyStack.push(num);
                    out.println("ok");
                    break;
                default:
                    break;
            }
        }while(!action.equals("exit"));
        out.println("bye");
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
