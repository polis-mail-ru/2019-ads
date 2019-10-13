package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    static class Stack {
        private static final int INITIAL_CAPACITY = 10;
        private static final int SCALE_FACTOR = 2;

        private int[] array;
        private int tip;

        Stack() {
            array = new int[INITIAL_CAPACITY];
            tip = -1;
        }

        void push(int n) {
            ++tip;
            if (tip == array.length) {
                final int[] newArr = new int[array.length * SCALE_FACTOR];
                System.arraycopy(array, 0, newArr, 0, size() - 1);
                array = newArr;
            }
            array[tip] = n;
        }

        int pop() {
            if (tip < 0) {
                throw new NoSuchElementException("Stack is empty");
            }
            return array[tip--];
        }

        int back() {
            if (tip < 0) {
                throw new NoSuchElementException("Stack is empty");
            }
            return array[tip];
        }

        int size() {
            return tip + 1;
        }

        void clear() {
            tip = -1;
        }
    }

    public static void main(final String[] arg) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Stack stack = new Stack();

        String[] command = new String[1];
        try {
            command = reader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!command[0].equalsIgnoreCase("exit")) {
            switch (command[0]) {
                case "push":
                    stack.push(Integer.parseInt(command[1]));
                    System.out.println("ok");
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (NoSuchElementException e) {
                        System.out.println("error");
                    }
                    break;
                case "back":
                    try {
                        System.out.println(stack.back());
                    } catch (NoSuchElementException e) {
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
            try {
                command = reader.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("bye");
    }
}