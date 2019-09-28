package ru.mail.polis.ads.part1.BogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public class OwnQueue {

    private final double loadFactor;

    private int[] elements;
    private int tail;
    private int head;
    private int size;
    private int maxSize;
    private PrintWriter pw;

    private OwnQueue() {
        maxSize = 16;
        elements = new int[maxSize];
        loadFactor = 0.75d;
        tail = -1;
        head = 0;
        size = 0;
        pw = new PrintWriter(System.out);
    }

    private OwnQueue(int maxSize) {
        if (maxSize < 1) {
            this.maxSize = 1;
        } else {
            this.maxSize = maxSize;
        }
        elements = new int[maxSize];
        loadFactor = 0.75d;
        tail = -1;
        head = 0;
        size = 0;
        pw = new PrintWriter(System.out);
    }

    private OwnQueue(int maxSize, double loadFactor) {
        if (maxSize < 1) {
            this.maxSize = 1;
        } else {
            this.maxSize = maxSize;
        }
        if ((loadFactor > 1) || (loadFactor < 0.5)) {
            this.loadFactor = 0.75d;
        } else {
            this.loadFactor = loadFactor;
        }
        elements = new int[maxSize];
        tail = -1;
        head = 0;
        size = 0;
        pw = new PrintWriter(System.out);
    }

    private OwnQueue(double loadFactor) {
        maxSize = 16;
        elements = new int[maxSize];
        if ((loadFactor > 1) || (loadFactor < 0.5)) {
            this.loadFactor = 0.75d;
        } else {
            this.loadFactor = loadFactor;
        }
        tail = -1;
        head = 0;
        size = 0;
        pw = new PrintWriter(System.out);
    }

    private int size() {
        pw.println(size);
        return size;
    }

    private void push(int element) {
        if (size == 0) {
            head = 0;
            tail = -1;
        }

        elements[++tail] = element;
        size++;

        if (tail > maxSize * loadFactor) {
            maxSize *= 2;
            int []temp = elements;
            elements = new int[maxSize];
            for (int i = 0; i < size; i++) {
                elements[i] = temp[head + i];
            }
            head = 0;
            tail = size - 1;
        }

        pw.println("ok");
    }

    private int front() {
        if (size == 0) {
            throw new NullPointerException("queue is empty");
        }
        pw.println(elements[head]);
        return elements[head];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void clear() {
        maxSize = 16;
        elements = new int[maxSize];
        size = 0;
        head = 0;
        tail = -1;
        pw.println("ok");
    }

    private int pop() {
        if (size == 0) {
            throw new NullPointerException("queue is empty");
        }
        size--;
        pw.println(elements[head]);
        return elements[head++];
    }

    private void exit() {
        pw.println("bye");
        pw.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        OwnQueue queue = new OwnQueue(100);
        Scanner scanner = new Scanner(System.in);
        String[] in;
        while (true) {
            in = scanner.nextLine().trim().split(" ");
            switch (in[0]) {
                case "push" : {
                    queue.push(Integer.parseInt(in[1]));
                    break;
                }
                case "pop" : {
                    queue.pop();
                    break;
                }
                case "front" : {
                    queue.front();
                    break;
                }
                case "size" : {
                    queue.size();
                    break;
                }
                case "clear" : {
                    queue.clear();
                    break;
                }
                case "exit" : {
                    queue.exit();
                }
                default : {
                    throw new IllegalArgumentException("Wrong command");
                }
            }
        }
    }
}
