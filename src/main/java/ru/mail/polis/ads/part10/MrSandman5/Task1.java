package part10;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/6399927

public class Task1 {

    private static class Pair{
        private int end;
        private int id;

        Pair(int end, int id) {
            this.end = end;
            this.id = id;
        }
    }

    private static int timer;
    private static int[] tin, fup;
    private static List<Pair>[] graph;
    private static int bridgeNumber;
    private static boolean[] isBridge;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        tin = new int [n + 1];
        fup = new int [n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            tin[i] = 0;
            fup[i] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            int a = in.nextInt(), b = in.nextInt();
            graph[a].add(new Pair(b, i));
            graph[b].add(new Pair(a, i));
        }
        bridgeNumber = 0;
        isBridge = new boolean[m + 1];
        findBridges(n);
        out.println(bridgeNumber);
        for (int i = 1; i < m + 1 && bridgeNumber > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgeNumber--;
            }
        }
    }

    private static void findBridges(int n) {
        timer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int nodeId, int prevLinkId) {
        tin[nodeId] = fup[nodeId] = ++timer;
        for (Pair link : graph[nodeId]) {
            if (link.id == prevLinkId) {
                continue;
            }
            if (tin[link.end] == 0) {
                dfs(link.end, link.id);
                fup[nodeId] = Math.min(fup[nodeId], fup[link.end]);
            } else {
                fup[nodeId] = Math.min(fup[nodeId], tin[link.end]);
            }
        }
        if (prevLinkId != -1 && fup[nodeId] == tin[nodeId]) {
            bridgeNumber++;
            isBridge[prevLinkId] = true;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
