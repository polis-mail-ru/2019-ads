package ru.mail.polis.ads.part9.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.*;

//e-olymp problem 4853 "Кратчайший путь"

public class Task5 {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        final int a = in.nextInt();
        final int b = in.nextInt();

        List<Integer>[] nodes = new LinkedList[n + 1];
        int[] previous = new int[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = -1;
            previous[i] = -1;
            nodes[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            nodes[first].add(second);
            nodes[second].add(first);
        }
        previous[a] = a;
        distance[a] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {

            Integer current = queue.poll();

            if (current == b) {
                break;
            }

            for (Integer integer: nodes[current]) {
                if (previous[integer] == -1) {
                    previous[integer] = current;
                    distance[integer] = distance[current] + 1;
                    queue.add(integer);
                }
            }

        }
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        out.println(distance[b]);
        if (distance[b] != -1) {
            int current = b;
            Stack<Integer> stack = new Stack<>();
            while (current != a) {
                stack.push(previous[current]);
                current = previous[current];
            }
            while (!stack.empty()) {
                out.print(stack.pop() + " ");
            }
            out.print(b);
        }
        out.flush();
    }
}
