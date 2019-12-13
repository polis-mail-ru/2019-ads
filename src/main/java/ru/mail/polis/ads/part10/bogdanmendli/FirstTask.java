package ru.mail.polis.ads.part10.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FirstTask {

    private static class Link {

        private int firstId;
        private int secondId;
        private int id;

        public Link(int firstId, int secondId, int id) {
            this.firstId = firstId;
            this.secondId = secondId;
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

    private static int n;
    private static int m;
    private static int timer;
    private static ArrayList<Integer> bridges;
    private static int[] tin;
    private static int[] fup;
    private static List<List<Integer>> nodes;
    private static Link[] links;
    private static byte[] used;

    private static void solve() {
        try (PrintWriter printWriter = new PrintWriter(System.out)) {

            FastScanner fastScanner = new FastScanner(System.in);
            n = fastScanner.nextInt();
            m = fastScanner.nextInt();

            used = new byte[n + 1];
            Arrays.fill(used, (byte) 0);

            nodes = new ArrayList<>(n + 1);
            for (int i = 0; i < n + 1; i++) {
                nodes.add(new ArrayList<>(100));
            }

            links = new Link[m + 1];
            for (int i = 1; i <= m; i++) {
                final int from = fastScanner.nextInt();
                final int to = fastScanner.nextInt();

                links[i] = new Link(from, to, i);

                nodes.get(from).add(to);
                nodes.get(to).add(from);
            }

            fup = new int[n + 1];
            tin = new int[n + 1];

            bridges = new ArrayList<>(100);
            findBridges();

            printWriter.println(bridges.size());
            if (bridges.isEmpty()) {
                System.exit(0);
            }

            bridges.trimToSize();
            bridges.sort(Integer::compare);
            for (int id : bridges) {
                printWriter.print(id);
                printWriter.print(" ");
            }
        }
    }

        private static void findBridges() {
        timer = 1;
        for (int i = 1; i <= n; i++) {
            if (used[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int nodeId, int parentId) {
        used[nodeId] = 1;
        tin[nodeId] = fup[nodeId] = timer++;

        nodes.get(nodeId).forEach(subNodeId -> {
            if (subNodeId != parentId) {
                if (used[subNodeId] == 1) {
                    fup[nodeId] = Math.min(fup[nodeId], tin[subNodeId]);
                } else {
                    dfs(subNodeId, nodeId);
                    fup[nodeId] = Math.min(fup[nodeId], fup[subNodeId]);
                    if (fup[subNodeId] > tin[nodeId] && subNodeId != nodeId) {
                        markBridge(nodeId, subNodeId);
                    }
                }
            }
        });
    }

    private static void markBridge(int firstId, int secondId) {
        for (int i = 1; i < links.length; i++) {
            Link link = links[i];
            if (link.firstId == firstId && link.secondId == secondId
                || link.firstId == secondId && link.secondId == firstId) {

                bridges.add(link.id);
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
