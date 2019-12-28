package ru.mail.polis.ads.part10.maksimshengeliia;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

/**
 * https://www.e-olymp.com/ru/submissions/6424644
 */
public class Task1 {

    private static int counter = 0, bridgesNum = 0, vertexesNum, edgesNum;
    private static int[] in, up;
    private static List<Map<Integer, Integer>> graph;
    private static boolean[] isBridge;


    private static void dfs(int curr, int parent) {
        counter++;
        in[curr] = counter;
        up[curr] = counter;
        Map<Integer, Integer> neibors = graph.get(curr);
        for (Map.Entry<Integer, Integer> n : neibors.entrySet()) {
            if (n.getValue() == parent) {
                continue;
            }
            if (in[n.getKey()] == 0) {
                dfs(n.getKey(), n.getValue());
                up[curr] = Math.min(up[curr], up[n.getKey()]);
            } else {
                up[curr] = Math.min(up[curr], in[n.getKey()]);
            }
        }
        if (parent != 0 && up[curr] == in[curr]) {
            bridgesNum++;
            isBridge[parent] = true;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        vertexesNum = in.nextInt();
        edgesNum = in.nextInt();

        graph = new ArrayList<>();
        Task1.in = new int[vertexesNum + 1];
        up = new int[vertexesNum + 1];

        for (int i = 0; i < vertexesNum + 1; i++) {
            graph.add(i, new HashMap<>());
            Task1.in[i] = 0;
            up[i] = 0;
        }

        for (int i = 1; i < edgesNum + 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).put(b, i);
            graph.get(b).put(a, i);
        }

        isBridge = new boolean[edgesNum + 1];
        findBridges();

        out.println(bridgesNum);
        for (int i = 1; bridgesNum > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgesNum--;
            }
        }
    }

    private static void findBridges() {
        for (int i = 1; i < vertexesNum + 1; i++) {
            if (in[i] == 0) {
                dfs(i, 0);
            }
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
