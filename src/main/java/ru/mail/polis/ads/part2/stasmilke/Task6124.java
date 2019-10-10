package ru.mail.polis.ads.part2.stasmilke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task6124 {
    private Task6124() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        InfinityStack queue = new InfinityStack();
        String command;
        while (!(command = in.next()).isEmpty()) {
            switch (command) {
                case "push" :
                    out.println(queue.push(Integer.parseInt(in.next())));
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "back":
                    out.println(queue.back());
                    break;
                case "clear":
                    out.println(queue.clear());
                    break;
                case "exit":
                    out.println("bye");
                    return;
            }
        }
    }

    private static class InfinityStack {
        private List<int[]> list;
        private int end;
        private long size;
        private int currentArrNum;
        private int[] currentArr;
        private static final int ARRAY_SIZE = 10000;

        InfinityStack() {
            list = new ArrayList<>(2);
            end = ARRAY_SIZE;
            size = 0;
            currentArrNum = -1;
            currentArr = new int[100];
        }

        String push(int n) {
            size++;
            if (end == ARRAY_SIZE) {
                end = 0;
                currentArrNum++;
                currentArr = new int[ARRAY_SIZE];
                list.add(currentArrNum, currentArr);
            }
            currentArr[end] = n;
            end++;
            return "ok";
        }

        String pop() {
            if (size == 0) {
                return "error";
            }
            size--;
            if (end == 0) {
                currentArrNum--;
                int outer = currentArr[end];
                currentArr = list.get(currentArrNum);
                end = ARRAY_SIZE - 1;
                return String.valueOf(outer);
            }
            return String.valueOf(currentArr[--end]);
        }

        String back() {
            if (size == 0) {
                return "error";
            }
            return String.valueOf(currentArr[end - 1]);
        }

        long size() {
            return size;
        }

        String clear() {
            list = new ArrayList<>(2);
            end = ARRAY_SIZE;
            size = 0;
            currentArrNum = -1;
            currentArr = new int[ARRAY_SIZE];
            return "ok";
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
