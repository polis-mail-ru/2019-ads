package ru.mail.polis.ads.part10.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Task1 {
    static List<Edge> edgesList[];
    static int time;
    static int enter[];
    static int ret[];
    static boolean isBridge[];
    static int amountOfBridges;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        time = 0;
        amountOfBridges = 0;
        enter = new int[n + 1];
        ret = new int[n + 1];
        isBridge = new boolean[m + 1];
        edgesList = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            edgesList[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; i++) {
            int b = fs.nextInt();
            int e = fs.nextInt();
            edgesList[b].add(new Edge(e, i));
            edgesList[e].add(new Edge(b, i));
        }

        dfs(1, 0);

        System.out.println(amountOfBridges);
        for (int i = 1; i <= m; i++) {
            if (isBridge[i] == true) {
                System.out.print(i + " ");
            }
        }

    }


    public static void dfs(int v, int prevEdge) {
        time = time + 1;
        enter[v] = time;
        ret[v] = time;

        for (Edge edge : edgesList[v]) {
            int u = edge.vertex;
            int id = edge.id;

            if (id == prevEdge) {
                continue;
            }

            // enter[u] == 0 means that vertex u was not visited
            if (enter[u] == 0) {
                dfs(u, id);
                ret[v] = Math.min(ret[v], ret[u]);
                if (ret[u] > enter[v]) {
                    isBridge[id] = true;
                    amountOfBridges++;
                }
            } else {
                ret[v] = Math.min(ret[v], enter[u]);
            }
        }
    }

    private static class Edge {
        private int vertex;
        private int id;

        public Edge(int vertex, int id) {
            this.vertex = vertex;
            this.id = id;
        }
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
