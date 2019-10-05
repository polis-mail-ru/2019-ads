package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5785159
 */
public class Problem5 {

    static class Stack {

        private static final int SCALE_FACTOR = 2;
        private static final int INITIAL_CAPACITY = 20;

        private int[] array;
        private int top;

        Stack() {
            array = new int[INITIAL_CAPACITY];
            top = -1;
        }

        void push(int n) {
            top++;
            if (top == array.length) {
                final int[] newArr = new int[array.length * SCALE_FACTOR];
                System.arraycopy(array, 0, newArr, 0, size() - 1);
                array = newArr;
            }
            array[top] = n;
        }

        int pop() {
            if (top == -1) {
                throw new NoSuchElementException("Stack is empty");
            }
            return array[top--];
        }

        int back() {
            if (top == -1) {
                throw new NoSuchElementException("Stack is empty");
            }
            return array[top];
        }

        int size() {
            return top + 1;
        }

        void clear() {
            top = -1;
        }
    }

    private Problem5() {

    }

    private static void solve() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final Stack queue = new Stack();

        while (true) {
            final String[] cmd = reader.readLine().split(" ");
            if (cmd[0].equalsIgnoreCase("exit")) {
                break;
            }
            switch (cmd[0]) {
                case "push": {
                    queue.push(Integer.parseInt(cmd[1]));
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    try {
                        System.out.println(queue.pop());
                    } catch (NoSuchElementException e) {
                        System.out.println("error");
                    }
                    break;
                }
                case "back": {
                    try {
                        System.out.println(queue.back());
                    } catch (NoSuchElementException e) {
                        System.out.println("error");
                    }
                    break;
                }
                case "size": {
                    System.out.println(queue.size());
                    break;
                }
                case "clear": {
                    queue.clear();
                    System.out.println("ok");
                    break;
                }
                default: {
                    break;
                }
            }
        }
        System.out.println("bye");
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
