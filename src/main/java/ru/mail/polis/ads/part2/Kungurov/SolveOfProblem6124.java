package ru.mail.polis.ads.part2.Kungurov;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by kirill.kungurov on 15.09.2019
 * https://www.e-olymp.com/ru/submissions/5861368
 */
public final class SolveOfProblem6124 {
    private SolveOfProblem6124() {
    }

    private static void solve(final BufferedReader in) throws IOException {
        final Stack stack = new Stack();
        String response = "";

        while (!"exit".equals(response)) {
            response = in.readLine().trim();
            String[] responseParams = response.split(" ");
            try {
                switch (responseParams[0]) {
                    case "push":
                        stack.push(Integer.parseInt(responseParams[1]));
                        System.out.println("ok");
                        break;
                    case "pop":
                        System.out.println(stack.pop());
                        break;
                    case "back":
                        System.out.println(stack.back());
                        break;
                    case "size":
                        System.out.println(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        System.out.println("ok");
                        break;
                    case "exit":
                        System.out.println("bye");
                        break;
                    default:
                        break;
                }
            } catch (Error e) {
                System.out.println("error");
            }
        }
    }

    private static class Stack {
        private int index = 0;
        private int[] stack = new int[index];

        void push(int element) {
            if (index >= stack.length) {
                rewrite();
            }
            stack[index] = element;
            index += 1;
        }

        int pop() {
            if (index < 1) {
                throw new Error();
            } else {
                index -= 1;
                return stack[index];
            }
        }

        int back() {
            if (index < 1) {
                throw new Error();
            } else {
                return stack[index - 1];
            }
        }

        int size() {
            return index;
        }

        void clear() {
            index = 0;
        }

        private void rewrite() {
            stack = Arrays.copyOf(stack, stack.length * 3+5);
        }
    }

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solve(in);
    }
}
