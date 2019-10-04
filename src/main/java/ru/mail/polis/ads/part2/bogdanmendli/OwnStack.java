package ru.mail.polis.ads.part2.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class OwnStack {

    private int[] data;
    private int size;

    private OwnStack() {
        this.data = new int[10000];
        this.size = 0;
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

    private void push(int n) {
        if (size == data.length - 1) {
            int[] temp = new int[data.length * 5];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
        data[size++] = n;
    }

    private Integer pop() {
        if (size == 0) {
            return null;
        }
        return (data[--size]);
    }

    private Integer back() {
        if (size == 0) {
            return null;
        }
        return (data[size - 1]);
    }

    private void clear() {
        size = 0;
    }

    private int size() {
        return size;
    }

    public static void main(final String[] args) {
        final OwnStack stack = new OwnStack();
        final FastScanner scanner = new FastScanner(System.in);
        while (true) {
            final String command = scanner.next();
            if ("exit".equals(command)) {
                break;
            }
            switch (command) {
                case "push" : {
                    stack.push(scanner.nextInt());
                    System.out.println("ok");
                    break;
                }
                case "pop" : {
                    Integer integer = stack.pop();
                    if (integer != null) {
                        System.out.println(integer);
                        break;
                    }
                    System.out.println("error");
                    break;
                }
                case "back" : {
                    Integer integer = stack.back();
                    if (integer != null) {
                        System.out.println(integer);
                        break;
                    }
                    System.out.println("error");
                    break;
                }
                case "size" : {
                    System.out.println(stack.size());
                    break;
                }
                case "clear" : {
                    stack.clear();
                    System.out.println("ok");
                    break;
                }
                default : {
                    break;
                }
            }
        }
        System.out.println("bye");
    }
}
