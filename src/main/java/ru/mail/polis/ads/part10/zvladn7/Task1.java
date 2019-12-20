package ru.mail.polis.ads.part10.zvladn7;

import java.io.*;
import java.util.ArrayList;

public class Task1 {

    static class Edge {
        int end;
        int order;

        Edge(int end, int order) {
            this.end = end;
            this.order = order;
        }
    }

    private static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    private static int amountOfBridges;
    private static int amountOfNodes;
    private static int amountOfEdges;
    private static boolean[] isBridge;
    private static int timer;
    private static int[] tin;
    private static int[] fup;

    private static void readGraph(final BufferedReader in) {
        try {
            String[] next = in.readLine().split(" ");
            amountOfNodes = Integer.parseInt(next[0]);
            amountOfEdges = Integer.parseInt(next[1]);

            fup = new int[amountOfNodes + 1];
            tin = new int[amountOfNodes + 1];
            isBridge = new boolean[amountOfEdges + 1];
            for (int i = 0; i < amountOfNodes + 1; ++i) {
                graph.add(new ArrayList<>());
                fup[i] = 0;
                tin[i] = 0;
            }
            timer = 0;
            amountOfBridges = 0;

            for (int i = 0; i < amountOfEdges; ++i) {
                next = in.readLine().split(" ");
                int firstNode = Integer.parseInt(next[0]);
                int secondNode = Integer.parseInt(next[1]);

                graph.get(firstNode).add(new Edge(secondNode, i + 1));
                graph.get(secondNode).add(new Edge(firstNode, i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int node, int previousNode) {
        tin[node] = fup[node] = ++timer;
        for (Edge edge : graph.get(node)) {
            int toNode = edge.end;
            if (edge.order == previousNode) {
                continue;
            }

            if (tin[toNode] != 0) {
                fup[node] = Math.min(fup[node], tin[toNode]);
            } else {
                dfs(toNode, edge.order);
                fup[node] = Math.min(fup[node], fup[toNode]);
            }
        }

        if (previousNode != -1 && fup[node] == tin[node]) {
            isBridge[previousNode] = true;
            ++amountOfBridges;
        }
    }

    private static void findBridges() {
        for (int i = 1; i < amountOfNodes + 1; ++i) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void printResult(final PrintWriter out) {
        out.println(amountOfBridges);
        for (int i = 1; i < amountOfEdges + 1; ++i) {
            if (isBridge[i]) {
                out.printf("%d ", i);
            }
        }
    }

    private static void solve(final BufferedReader in, final PrintWriter out) {
        readGraph(in);
        findBridges();
        printResult(out);
    }

    public static void main(final String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }

}
