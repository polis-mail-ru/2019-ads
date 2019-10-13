package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new ArrayDeque<>();
        String s;
        do {
            s = br.readLine();
            if (s.contains("push")) {
                deque.add(Integer.parseInt(s.substring(5)));
                System.out.println("ok");
            } else if (s.contains("pop")) {
                if (deque.size() == 0) {
                    System.out.println("error");
                } else {
                    System.out.println(deque.pollLast());
                }
            } else if (s.contains("back")) {
                if (deque.size() == 0) {
                    System.out.println("error");
                } else {
                    System.out.println(deque.getLast());
                }
            } else if (s.contains("size")) {
                System.out.println(deque.size());
            } else if (s.contains("clear")) {
                deque.clear();
                System.out.println("ok");
            } else if (s.contains("exit")) {
                System.out.println("bye");
            }
        } while (!s.equals("exit"));
    }
}