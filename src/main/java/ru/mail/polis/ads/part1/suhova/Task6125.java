package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Task6125 {
    /*
    https://www.e-olymp.com/ru/submissions/5722133
     */

    private static void solve(final Task6125.FastScanner in, final PrintWriter out) {
        ArrayDeque<Integer> q=new ArrayDeque<>();
        String command=in.next();
        while(!command.contains("exit")){
            switch (command){
                case "push":
                    Integer x=Integer.parseInt(in.next());
                    q.addLast(x);
                    out.println("ok");
                    break;
                case "pop":
                    out.println(q.pop());
                    break;
                case "front":
                    out.println(q.getFirst());
                    break;
                case "size":
                    int s=q.size();
                    out.println(s);
                    break;
                case "clear":
                    q.clear();
                    out.println("ok");
                    break;
            }
            command=in.next();
        }
        out.println("bye");
        out.flush();
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
        final Task6125.FastScanner in = new Task6125.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}