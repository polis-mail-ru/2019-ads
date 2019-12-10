package ru.mail.polis.ads.suhova.part9;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 1: https://www.e-olymp.com/ru/submissions/6236967
 */
public class Task1948 {
    private static int[] color;
    private static ArrayList<ArrayDeque<Integer>> v;
    private static ArrayDeque<Integer> res;
    private static boolean error = false;

    public static void main(final String[] arg) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        color = new int[n + 1];
        v = new ArrayList<>(n);
        res = new ArrayDeque<>(n);
        for (int i = 0; i <= n; i++) {
            v.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            v.get(start).push(end);
        }
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                dfs(i);
            }
        }
        if (error || res.size() != n) System.out.println(-1);
        else {
            for (Integer x : res) {
                System.out.print(x + " ");
            }
        }
    }

    private static void dfs(int node) {
        color[node] = 1;
        for (Integer u : v.get(node)) {
            if (color[u] == 0) {
                dfs(u);
            } else if (color[u] == 1) {
                error = true;
                break;
            }
        }
        color[node] = 2;
        res.addFirst(node);
    }
}