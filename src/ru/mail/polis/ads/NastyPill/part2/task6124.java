package ru.mail.polis.ads.NastyPill.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
enum Commands {
    PUSH, POP, BACK, SIZE, CLEAR, EXIT;
}

class MyStack {
    LinkedList<Integer> list;

    public MyStack() {
        list = new LinkedList<>();
    }

    public int getSize() {
        return list.size();
    }

    public int pop() {
        int res = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return res;
    }

    public void push(int num) {
        list.add(num);
    }

    public int back() {
        return list.get(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }
}

public final class task6124 {
    private task6124() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5778641
    }



    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack stack = new MyStack();
        String command = "";
        while (!command.equals(Commands.EXIT.name())) {
            command = in.next().toUpperCase();
            switch (Commands.valueOf(command)) {
                case EXIT: {
                    System.out.print("bye");
                }
                break;

                case POP: {
                    try {
                        System.out.println(stack.pop());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                }
                break;

                case PUSH: {
                    stack.push(in.nextInt());
                    System.out.println("ok");
                }
                break;

                case SIZE: {
                    System.out.println(stack.getSize());
                }
                break;

                case CLEAR: {
                    stack.clear();
                    System.out.println("ok");
                }
                break;

                case BACK: {
                    try {
                        System.out.println(stack.back());
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("error");
                    }
                }
            }
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