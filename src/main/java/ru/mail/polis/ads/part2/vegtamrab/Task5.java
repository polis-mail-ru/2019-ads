package ru.mail.polis.ads.part2.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        InfinityStack infStack = new InfinityStack();
        String command;
        while (!(command = in.next()).isEmpty()) {
            switch (command) {
                case "push":
                    out.println(infStack.push(Integer.parseInt(in.next())));
                    break;
                case "size":
                    out.println(infStack.size());
                    break;
                case "pop":
                    out.println(infStack.pop());
                    break;
                case "back":
                    out.println(infStack.back());
                    break;
                case "clear":
                    out.println(infStack.clear());
                    break;
                case "exit":
                    out.println("bye");
                    return;
                    
            }
        }
    }


    private static class InfinityStack {
        private static final int ARRAY_SIZE = 32767;
        private List<int[]> list;
        private int end;
        private long size;
        private int currentArrNum;
        private int[] currentArr;

        InfinityStack() {
            list = new ArrayList<>(2);
            end = ARRAY_SIZE;
            size = 0;
            currentArrNum = -1;
            currentArr = new int[100];
        }

        String push(int n) {
            ++size;
            if (end == ARRAY_SIZE) {
                end = 0;
                ++currentArrNum;
                currentArr = new int[ARRAY_SIZE];
                list.add(currentArrNum, currentArr);
            }
            currentArr[end] = n;
            ++end;
            return "ok";
        }

        String pop() {
            if (size == 0) {
                return "error";
            }
            --size;
            if (end == 0) {
                --currentArrNum;
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
