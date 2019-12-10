package ru.mail.polis.ads.part9.KateMoreva;

import java.io.*;
import java.util.*;

//e-olymp problem 2022 "Циклы в графе"

public class Task2 {
    public static void main(String[] args) throws IOException{

        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] query = in.readLine().split(" ");
        final int n = Integer.parseInt(query[0]);

        PriorityQueue[] array = new PriorityQueue[n + 1];
        byte[] colors = new byte[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            array[i] = new PriorityQueue<>(8);
            colors[i] = 0;
            prev[i] = -1;
        }
        final int m = Integer.parseInt(query[1]);
        for (int i = 0; i < m; i++) {
            query = in.readLine().split(" ");
            final int a = Integer.parseInt(query[0]);
            final int b = Integer.parseInt(query[1]);
            array[a].add(b);
            array[b].add(a);
        }

        int min = n + 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < n + 1; i++) {
            if (min <= i) {
                break;
            }
            boolean hasCycle = false;
            if (colors[i] == 0) {
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                Integer current = stack.peek();

                if (colors[current] == 1) {
                    hasCycle = false;
                    min = Math.min(current, min);
                }
                colors[current] = 2;
                boolean isNext = false;

                while (!array[current].isEmpty()) {
                    if (hasCycle) {
                        break;
                    }
                    int tmp = (int) array[current].poll();
                    if (tmp == prev[current]) {
                        continue;
                    }

                    if (colors[tmp] == 2) {
                        hasCycle = true;
                        colors[tmp] = 1;
                        break;
                    }

                    if (colors[tmp] == 0) {
                        prev[tmp] = current;
                        stack.push(tmp);
                        isNext = true;
                        break;
                    }

                }
                if (isNext) {
                    continue;
                }
                if (hasCycle) {
                    min = Math.min(current, min);
                }
                if (colors[current] != 0) {
                    colors[current] = 3;
                    stack.pop();
                }

            }
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        if (min == n + 1) {
            out.println("No");
        } else {
            out.println("Yes\n" + min);
        }
        out.flush();
    }
}
