package ru.mail.polis.ads.part2.nik27090;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

//Задача: "Стек неограниченного размер"
//Рещение: https://www.e-olymp.com/ru/submissions/5842830

public class UnlimitedStack {

    private UnlimitedStack() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack<Integer> stack = new Stack<>();
        String command ="";

        while(!command.equals("exit")){
            command = in.next();
            switch (command){
                case "push":
                    stack.push(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    if (stack.size()!=0){
                        System.out.println(stack.pop());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "back":
                    if (stack.size()!=0){
                        System.out.println(stack.peek());
                    } else{
                        System.out.println("error");
                    }
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    System.out.println("ok");
                    break;
            }
        }
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