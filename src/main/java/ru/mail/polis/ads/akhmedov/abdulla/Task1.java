package main.java.ru.mail.polis.ads.akhmedov.abdulla;
import java.io.*;
import java.util.*;

public class Task1 {

    private static int n;
    private static int bridges;
    private static int timer;
    private static List<List<Edge>> graph;
    private static int[] tin;
    private static int[] fup;
    private static boolean[] isBridge;

    private static class Edge {
        private int to;
        private int id;

        public Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        try (final PrintWriter out = new PrintWriter(System.out)) {

            final FastScanner in = new FastScanner(System.in);
            n = in.nextInt();
            int m = in.nextInt();

            fup = new int[n + 1];
            tin = new int[n + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
                tin[i] = 0;
                fup[i] = 0;
            }

            for (int i = 1; i <= m; i++) {
                final int from = in.nextInt();
                final int to = in.nextInt();

                graph.get(from).add(new Edge(to, i));
                graph.get(to).add(new Edge(from, i));
            }

            bridges = 0;

            isBridge = new boolean[m + 1];
            Arrays.fill(isBridge, false);

            findBridges();

            out.println(bridges);
            for (int i = 1; i <= m && bridges > 0; i++) {

                if (isBridge[i]) {
                    out.print(i + " ");
                    bridges--;
                }
            }
        }
    }

    private static void findBridges() {
        timer = 0;
        for (int i = 1; i <= n; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int node, int previousId) {
        tin[node] = fup[node] = ++timer;

        for (Edge edge : graph.get(node)) {
            if (edge.id == previousId) {
                continue;
            }
            if (tin[edge.to] == 0) {
                dfs(edge.to, edge.id);
                fup[node] = Math.min(fup[node], fup[edge.to]);
            } else {
                fup[node] = Math.min(fup[node], tin[edge.to]);
            }
        }

        if (previousId != -1 && fup[node] == tin[node]) {
            bridges++;
            isBridge[previousId] = true;
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
