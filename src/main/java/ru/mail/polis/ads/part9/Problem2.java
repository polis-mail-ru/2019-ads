package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * submission - https://www.e-olymp.com/ru/submissions/6401169
 */
public class Problem2 {

    private static List<List<Integer>> nodes;
    private static boolean[] inCycle;
    private static byte[] nodeState;
    private static int[] previous;

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        final int n = Integer.parseInt(tokens[0]);
        final int m = Integer.parseInt(tokens[1]);
        nodes = new ArrayList<>(n + 1);
        nodeState = new byte[n + 1];
        inCycle = new boolean[n + 1];
        previous = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] link = reader.readLine().split(" ");
            int from = Integer.parseInt(link[0]);
            int to = Integer.parseInt(link[1]);

            nodes.get(from).add(to);
            nodes.get(to).add(from);
        }

        sort();

        for (int i = 0; i < inCycle.length; i++) {
            if (inCycle[i]) {
                System.out.println("Yes");
                System.out.println(i);
                return;
            }
        }
        System.out.println("No");
    }

    private static void sort() {
        for (int i = 1; i < nodes.size(); i++) {
            if (nodeState[i] == 0) {
                dfs(i);
            }
        }
    }

    private static void dfs(int node) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            int current = stack.peek();
            nodeState[current] = 1;
            boolean isInCycle = true;

            for (int adjacent : nodes.get(current)) {
                if (previous[current] == adjacent || nodeState[adjacent] == 2) {
                    continue;
                }
                if (nodeState[adjacent] == 0) {
                    previous[adjacent] = current;
                    stack.push(adjacent);
                    isInCycle = false;
                    break;
                } else if (nodeState[adjacent] == 1) {
                    int i = current;
                    inCycle[adjacent] = true;
                    if (inCycle[i]) {
                        continue;
                    }
                    while (i != adjacent) {
                        inCycle[i] = true;
                        i = previous[i];
                        if (inCycle[i]) {
                            break;
                        }
                    }
                }
            }
            if (isInCycle) {
                stack.pop();
                nodeState[current] = 2;
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
