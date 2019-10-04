package ru.mail.polis.ads.part1.nik27090;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* Задача: Простая очередь
* Решение: https://www.e-olymp.com/ru/submissions/5780101
*/

public class SimpleQueue {

        private SimpleQueue() {
            // Should not be instantiated
        }

        private static void solve(final FastScanner in, final PrintWriter out) {
            Queue<Integer> que = new ArrayDeque();
            String input = "";
            while (!input.equals("exit")){
                input = in.next();
                switch (input){
                    case "push":
                        que.offer(in.nextInt());
                        System.out.println("ok");
                        break;
                    case "pop":
                        System.out.println(que.poll());
                        break;
                    case "front":
                        System.out.println(que.peek());
                        break;
                    case "size":
                        System.out.println(que.size());
                        break;
                    case "clear":
                        que = new ArrayDeque<>();
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