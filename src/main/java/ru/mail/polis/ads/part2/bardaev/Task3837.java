package ru.mail.polis.ads.part2.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Task3837 {
    private Task3837() {}

    private static void solve(final FastScanner in, final PrintWriter out) {
        String result = "";
        int a = in.nextInt();
        ArrayDeque<String> expressions = new ArrayDeque<>();
        for (int i = 0; i < a; i++) {
            expressions.addLast(in.next());
        }

        MyStack[] res = new MyStack[a];
        for (int i = 0; i < a; i++) {
            MyStack stack = new MyStack();
            String str = expressions.poll();
            for (int j = 0; j < str.length(); j++) {
                if (Character.isLowerCase(str.charAt(j))) {
                    MyQueue m = new MyQueue();
                    m.expr = String.valueOf(str.charAt(j));
                    stack.push(m);
                } else if (Character.isUpperCase(str.charAt(j))) {
                    MyQueue q = new MyQueue();
                    q.expr = String.valueOf(str.charAt(j));
                    q.rightVal = stack.poll();
                    q.leftVal = stack.poll();
                    stack.push(q);
                }
            }
            res[i] = stack;
            stack = null;
        }

        ArrayDeque<MyQueue> arr = new ArrayDeque<>();
        for (int i = 0; i < res.length; i++) {
            MyStack st = res[i];
            arr.addLast(st.poll());
            while (arr.size() > 0) {
                MyQueue q = arr.poll();
                result += q.expr;
                if (!(q.rightVal == null && q.leftVal == null)) {
                    arr.addLast(q.leftVal);
                    arr.addLast(q.rightVal);
                }
            }

            StringBuffer s = new StringBuffer(result);
            out.println(s.reverse());
            result = "";
            out.flush();
        }

    }

    private static class MyQueue {
        String expr;
        MyQueue leftVal;
        MyQueue rightVal;
        MyQueue () {
            this.expr = null;
            this.leftVal = null;
            this.rightVal = null;
        }
    }

    private static class MyStack {
        private int count;
        private ArrayList<MyQueue> arr;

        MyStack() {
            this.count = 0;
            this.arr = new ArrayList<>();
        }

        public void push(MyQueue s) {
            arr.add(s);
            count++;
        }

        public MyQueue poll() {
            if (count > 0) {
                MyQueue temp = arr.remove(count-1);
                count--;
                return temp;
            }
            return null;
        }

        public int size() {
            return count;
        }
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
