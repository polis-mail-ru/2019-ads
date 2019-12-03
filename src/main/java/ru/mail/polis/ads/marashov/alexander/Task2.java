package ru.mail.polis.ads.marashov.alexander;

import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int n = Integer.parseInt(in.next());
        Queue<Integer>[] array = new PriorityQueue[n + 1];
        char[] colors = new char[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            array[i] = new PriorityQueue<>(8);
            colors[i] = 'w';
            prev[i] = -1;
        }

        final int m = Integer.parseInt(in.next());
        for (int i = 1; i <= m; ++i) {
            final int first = Integer.parseInt(in.next());
            final int second = Integer.parseInt(in.next());
            array[first].add(second);
            array[second].add(first);
        }

        int min = n + 1;

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; ++i) {
            if (min <= i) {
                break;
            }
            boolean hasCycle = false;
            if (colors[i] == 'w') {
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer current = stack.peek();

                if (colors[current] == 'c') {
                    hasCycle = false;
                    min = Math.min(current, min);
                }

                colors[current] = 'g';

                boolean goNext = false;
                while(!array[current].isEmpty()) {
                    if (hasCycle) {
                        break;
                    }

                    int next = array[current].poll();

                    if (next == prev[current]) {
                        continue;
                    }

                    if (colors[next] == 'g') {
                        hasCycle = true;
                        colors[next] = 'c';
                        break;
                    }

                    if (colors[next] == 'w') {
                        prev[next] = current;
                        stack.push(next);
                        goNext = true;
                        break;
                    }
                }

                if (goNext) {
                    continue;
                }

                if (hasCycle) {
                    min = Math.min(current, min);
                }

                if (colors[current] != 'w') {
                    colors[current] = 'b';
                    stack.pop();
                }
            }
        }

        if (min == n + 1) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(min);
        }

    }
}
