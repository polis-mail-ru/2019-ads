package ru.mail.polis.ads.task9.art241111;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class FirstTask {
    private final static char BLACK = 'b';
    private final static char WHITE = 'w';
    private final static char GREY = 'g';


    private static List<Integer>[] arrayIn;
    private static char[] colors;
    private static Stack<Integer> answer;

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();
        final int n = in.nextInt();

        arrayIn = new ArrayList[n + 1];
        colors = new char[n + 1];
        for (int i = 0; i <= n; ++i) {
            arrayIn[i] = new ArrayList<>();
            colors[i] = WHITE;
        }

        final int m = in.nextInt();
        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            if (second > n || first > n) {
                out.append(-1).append("\n");
                return;
            }
            arrayIn[first].add(second);
        }

        answer = new Stack<>();
        for (int i = 1; i <= n; ++i) {
            topologicalSort(i);
        }

        while (!answer.empty()) {
            out.append(answer.pop()).append(" ");
        }
        System.out.println(out);
    }
        private static void topologicalSort(int current) {
            if (colors[current] == BLACK) {
                return;
            }
            if (colors[current] == GREY) {
                System.out.println(-1);
                System.exit(0);
                return;
            }

            colors[current] = GREY;

            for (Integer integer: arrayIn[current]) {
                topologicalSort(integer);
            }

            answer.push(current);
            colors[current] = BLACK;
        }
}
