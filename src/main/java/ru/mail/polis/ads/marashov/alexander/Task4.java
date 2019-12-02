package ru.mail.polis.ads.marashov.alexander;

import java.util.Scanner;
import java.util.Stack;

public class Task4 {
    public static void main(String[] args) {
        final int inf = 200000001;
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        final int s = in.nextInt();
        final int f = in.nextInt();

        boolean[] visited = new boolean[n]; // посещена ли вершина
        int[] dist = new int[n]; // расстояние до вершины от старта
        int[] prev = new int[n]; // массив обратного маршрута

        for (int i = 0; i < n; i++) {
            dist[i] = inf;
            visited[i] = false;
            prev[i] = inf;
        }

        int[][] mass = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                mass[i][j] = -1;
            }
        }

        for (int i = 0; i < m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            final int weight = in.nextInt();
            mass[first - 1][second - 1] = weight;
            mass[second - 1][first - 1] = weight;
        }

        int cnt = 0;
        dist[s - 1] = 0;
        int now = 0;

        while (cnt != n) {

            int mn = inf;

            for (int i = 0; i < n; i++) { // нахождение минимальной непосещённой вершины из набора вершин
                if (!visited[i] && dist[i] < mn) {
                    mn = dist[i];
                    now = i;
                }
            }

            for (int j = 0; j < n; j++) {
                if (mass[now][j] >= 0 && !visited[j] && dist[j] > mass[now][j] + dist[now]) { // если текущая вершина соединена с этой, не была посещена и будущее значение минимально
                    dist[j] = mass[now][j] + dist[now]; // дистанция до вершины j = до now + расстояние
                    prev[j] = now;
                }
            }
            visited[now] = true;
            cnt++;

        }

        if (dist[f - 1] == inf) {
            System.out.println(-1);
        } else {
            System.out.println(dist[f - 1]);
            int current = f - 1;
            Stack<Integer> stack = new Stack<>();
            while (current != s - 1) {
                stack.push(prev[current]);
                current = prev[current];
            }
            while (!stack.empty()) {
                System.out.print((stack.pop() + 1) + " ");
            }
            System.out.print(f + " ");
        }
    }
}
