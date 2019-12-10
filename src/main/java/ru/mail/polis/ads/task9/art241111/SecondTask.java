package ru.mail.polis.ads.task9.art241111;

import java.util.*;

public class SecondTask {

    private static List<List<Integer>> graph;
    private static boolean[] inCycle;
    private static byte[] used;
    private static int[] previous;

    private static void solve(){
        final Scanner in = new Scanner(System.in);

        final int n = in.nextInt();
        final int m = in.nextInt();
        final int MAX_VALUE = n + 1;
        int min = MAX_VALUE;

        graph = new ArrayList<>(n + 1);
        used = new byte[n + 1];
        inCycle = new boolean[n + 1];
        previous = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        sort();

        for (int i = 0; i < inCycle.length; i++) {
            if (inCycle[i] && min > i) {
                min = i;
            }
        }

        if (min == MAX_VALUE) {
            System.out.print("No");
        } else {
            System.out.print("Yes" + "\n" + min);
        }
    }

    private static void sort() {
        for (int i = 1; i < graph.size(); i++) {
            if (used[i] == 0) {
                dfs(i);
            }
        }
    }

    private static void dfs(int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            int tempNode = stack.peek();
            used[tempNode] = 1;
            boolean isNotInCycle = false;

            for (int subNode : graph.get(tempNode)) {
                if (previous[tempNode] == subNode || used[subNode] == 2) {
                    continue;
                }
                if (used[subNode] == 0) {
                    previous[subNode] = tempNode;
                    stack.push(subNode);
                    isNotInCycle = true;
                    break;
                } else if (used[subNode] == 1) {
                    int i = tempNode;
                    inCycle[subNode] = true;
                    if (inCycle[i]) {
                        continue;
                    }
                    while (i != subNode) {
                        inCycle[i] = true;
                        i = previous[i];
                        if (inCycle[i]) {
                            break;
                        }
                    }
                }
            }
            if (!isNotInCycle) {
                stack.pop();
                used[tempNode] = 2;
            }
        }
    }

    public static void main(String[] args) {
            solve();
    }
}