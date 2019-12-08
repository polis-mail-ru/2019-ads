package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SecondTask {

    private static List<List<Integer>> nodes;
    private static boolean[] inCycle;
    private static byte[] used;
    private static int[] previous;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersNodesAndLinks = br.readLine().split(" ");
        int minimum = Integer.MAX_VALUE;
        final int n = Integer.parseInt(numbersNodesAndLinks[0]);
        final int m = Integer.parseInt(numbersNodesAndLinks[1]);
        nodes = new ArrayList<>(n + 1);
        used = new byte[n + 1];
        inCycle = new boolean[n + 1];
        previous = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] link = br.readLine().split(" ");
            int from = Integer.parseInt(link[0]);
            int to = Integer.parseInt(link[1]);

            nodes.get(from).add(to);
            nodes.get(to).add(from);
        }

        sort();

        for (int i = 0; i < inCycle.length; i++) {
            if (inCycle[i] && minimum > i) {
                minimum = i;
            }
        }

        if (minimum == Integer.MAX_VALUE) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(minimum);
        }
    }

    private static void sort() {
        for (int i = 1; i < nodes.size(); i++) {
            if (used[i] == 0) {
                dfs(i);
            }
        }
    }

    private static void dfs(int node) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            int tempNode = stack.peek();
            used[tempNode] = 1;
            boolean isNotInCycle = false;

            for (int subNode : nodes.get(tempNode)) {
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
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
