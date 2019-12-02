package ru.mail.polis.ads.marashov.alexander;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Task1 {

    private final static char BLACK = 'b';
    private final static char WHITE = 'w';
    private final static char GREY = 'g';

    private static ArrayList<LinkedList<Integer>> array;
    private static char[] colors;
    private static boolean hasCycle = false;
    private static Stack<Integer> answer;

    private static void dfs(int current) {
        if (hasCycle) {
            return;
        }
        if (colors[current] != WHITE) {
            hasCycle |= colors[current] == GREY;
            return;
        }

        colors[current] = GREY;

        for (Integer integer: array.get(current)) {
            dfs(integer);
        }

        answer.push(current);
        colors[current] = BLACK;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int n = in.nextInt();
        array = new ArrayList<>(n);
        colors = new char[n + 1];
        for (int i = 0; i <= n; ++i) {
            array.add(new LinkedList<>());
            colors[i] = WHITE;
        }

        final int m = in.nextInt();
        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            if (second > n || first > n) {
                System.out.println(-1);
                return;
            }
            array.get(first).add(second);
        }



        answer = new Stack<>();
        for (int i = 1; i <= n; ++i) {
            dfs(i);
        }

        if (hasCycle) {
            System.out.println(-1);
        } else {
            while (!answer.empty()) {
                System.out.print(answer.pop() + " ");
            }
        }

    }
}
