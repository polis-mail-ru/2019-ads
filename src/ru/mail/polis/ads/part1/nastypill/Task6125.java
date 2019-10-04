package ru.mail.polis.ads.part1.nastypill;

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
//https://www.e-olymp.com/ru/submissions/5737030
enum Commands {
    EXIT(), POP(), PUSH(), FRONT(), SIZE(), CLEAR()
}


final class Queue {

    private Integer size;
    private LinkedList<Integer> list;

    public Queue() {
        size = 0;
        list = new LinkedList<>();
    }

    public Integer getSize() {
        return size;
    }

    public Integer peek() {
        return list.get(0);
    }

    public Integer pop() {
        int res = list.get(0);
        list.remove(0);
        size--;
        return res;
    }

    public Boolean push(int num) {
        size++;
        list.add(num);
        return true;
    }

    public Boolean clear() {
        while (size > 0) {
            pop();
        }
        return true;
    }
}

public final class Task6125 {
    private Task6125() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String command = "";
        while (!command.equals(Commands.EXIT.name())) {
            command = in.next().toUpperCase();
            switch (Commands.valueOf(command)) {
                case EXIT: {
                    System.out.print("bye");
                }
                break;

                case POP: {
                    System.out.println(queue.pop());
                }
                break;

                case PUSH: {
                    queue.push(in.nextInt());
                    System.out.println("ok");
                }
                break;

                case SIZE: {
                    System.out.println(queue.getSize());
                }
                break;

                case CLEAR: {
                    queue.clear();
                    System.out.println("ok");
                }
                break;

                case FRONT: {
                    System.out.println(queue.peek());
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