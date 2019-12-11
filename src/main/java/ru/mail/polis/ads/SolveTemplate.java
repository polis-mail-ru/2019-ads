package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class SolveTemplate {
    ArrayList<ArrayList<Integer>> graph;
    ArrayList<Integer> used, top;
    int a, b, i, n, m, Error;
    public void main(String[] args) {
        //Читаем входные данные. Строим список смежности графа.
        FastScanner fs = new FastScanner(System.in);
        graph.resize(n + 1); used.resize(n + 1);
        for (i = 0; i < n; i++) {
            scanf("%d %d", &a, &b), graph[a].push_back(b)
        };

        //Совершаем обход в глубину вершин графа.
        for (i = 1; i <= n; i++) {
            if (!used[i]) dfs(i);
        }

        //Если в графе присутствует цикл (при обходе в глубину установлено Error = 1), то выводим - 1.
        if (Error) {
            System.out.println("-1\n");
        } else {
            //Выводим вершины графа в порядке, обратном тому, в котором они заносились в массив top.
            for (i = n - 1; i > 0; i--) {
                System.out.println("%d ", top[i]);
            }
            System.out.println("%d\n", top[0]);
        }
    }

    void dfs(int i)
    {
        used[i] = 1;
        for (int j = 0; j < graph[i].size(); j++)
        {
            int to = graph[i][j];

            //Если ориентированное ребро (i, to) ведет в серую вершину, то в графе присутствует цикл.
            if (used[to] == 1) Error = 1;

            //Если вершина to еще не просмотрена, то рекурсивно запускаем из нее поиск в глубину.
            if (!used[to]) dfs(graph[i][j]);
        }
        used[i] = 2;
        top.push_back(i);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
