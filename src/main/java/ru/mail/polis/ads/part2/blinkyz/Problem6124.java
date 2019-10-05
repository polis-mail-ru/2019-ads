package ru.mail.polis.ads.part2.blinkyz;

import ru.mail.polis.ads.FastScanner;

import java.util.NoSuchElementException;

public class Problem6124 {
    private Problem6124() {
    }

    private static final class InfiniteStack {
        private static final int INITIAL_ARRAY_CAPACITY = 128;

        int capacity;

        int[] arr;

        int size;

        int top;

        InfiniteStack(final int capacity) {
            this.capacity = capacity;
            this.arr = new int[capacity];
            this.size = 0;
            this.top = -1;
        }

        InfiniteStack() {
            this(INITIAL_ARRAY_CAPACITY);
        }

        private void reallocateArray() {
            int[] oldArr = this.arr;
            final int newArrayCapacity = this.capacity * 2;
            int[] arr = new int[newArrayCapacity];
            this.capacity = newArrayCapacity;
            System.arraycopy(oldArr, 0, arr, 0, size);
            this.arr = arr;
        }

        void push(final int e) {
            if (top + 1 == capacity - 1) {
                reallocateArray();
            }
            arr[++top] = e;
            ++size;
        }

        int pop() throws NoSuchElementException {
            if (size == 0) {
                throw new NoSuchElementException("Stack is empty");
            }
            --size;
            return arr[top--];
        }

        int back() throws NoSuchElementException {
            if (size == 0) {
                throw new NoSuchElementException("Stack is empty");
            }
            return arr[top];
        }

        int size() {
            return size;
        }

        void clear() {
            top = -1;
            size = 0;
        }
    }

    private static void solve() {
        final InfiniteStack stack = new InfiniteStack();
        final FastScanner in = new FastScanner(System.in);

        while (true) {
            String cmd = in.next();
            switch (cmd) {
                case "push": {
                    stack.push(in.nextInt());
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    try {
                        System.out.println(stack.pop());
                    } catch (NoSuchElementException ex) {
                        System.out.println("error");
                    }
                    break;
                }
                case "back": {
                    try {
                        System.out.println(stack.back());
                    } catch (NoSuchElementException ex) {
                        System.out.println("error");
                    }
                    break;
                }
                case "size": {
                    System.out.println(stack.size());
                    break;
                }
                case "clear": {
                    stack.clear();
                    System.out.println("ok");
                    break;
                }
                case "exit": {
                    System.out.println("bye");
                    return;
                }
                default: {
                    throw new IllegalStateException("Unsupported or unknown operation: " + cmd);
                }
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
