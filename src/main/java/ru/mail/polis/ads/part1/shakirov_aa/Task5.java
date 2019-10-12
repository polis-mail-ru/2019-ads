package ru.mail.polis.ads.part1.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new ArrayDeque<>();
        String s;
        do {
            s = br.readLine();
            if (s.contains("push")) {
                queue.add(Integer.parseInt(s.substring(5)));
                System.out.println("ok");
            } else if (s.contains("pop")) {
                System.out.println(queue.poll());
            } else if (s.contains("front")) {
                System.out.println(queue.peek());
            } else if (s.contains("size")) {
                System.out.println(queue.size());
            } else if (s.contains("clear")) {
                queue.clear();
                System.out.println("ok");
            } else if (s.contains("exit")) {
                System.out.println("bye");
            }
        } while (!s.equals("exit"));
    }
}
