package ru.mail.polis.ads.part10.zvladn7;


import java.io.*;
import java.util.PriorityQueue;

public class Task3 {

    private static class Edge implements Comparable<Edge> {
        int firstNode;
        int secondNode;
        int danger;

        Edge(int firstNode, int secondNode, int weight) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.danger = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return danger - edge.danger;
        }
    }

    private static int amountOfNodes;
    private static int amountOfEdges;
    private static PriorityQueue<Edge> priorityEdges = new PriorityQueue<>();
    private static int maxPathDanger;
    private static int[] parent;
    private static int length = 0;
    private static int length1 = 0;
    private static int length2 = 0;

    private static int findSet(int v) {
        int current = v;
        while (v != parent[v]) {
            v = parent[v];
            ++length;
        }
        while (current != parent[current]) {
            current = parent[current];
            parent[current] = v;
        }
        parent[current] = v;
        return v;
    }

    private static void unionSets(int first, int second) {

        if (first != second) {
            if (length1 > length2) {
                parent[first] = second;
            } else {
                parent[second] = first;
            }
        }
    }

    private static void readGraph(final BufferedReader in) {
        try {
            String[] next = in.readLine().split(" ");
            amountOfNodes = Integer.parseInt(next[0]);
            amountOfEdges = Integer.parseInt(next[1]);

            parent = new int[amountOfNodes + 1];
            maxPathDanger = 0;

            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }
            for (int i = 0; i < amountOfEdges; ++i) {
                next = in.readLine().split(" ");
                int firstNode = Integer.parseInt(next[0]);
                int secondNode = Integer.parseInt(next[1]);
                int weight = Integer.parseInt(next[2]);

                priorityEdges.add(new Edge(firstNode, secondNode, weight));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void calculate() {
        int currentAddedAmount = 1;

        while (currentAddedAmount < amountOfNodes) {
            Edge edge = priorityEdges.poll();
            int first = findSet(edge.firstNode);
            int second = findSet(edge.secondNode);
            length1 = length;
            length2 = length;
            length1 = 0;
            if (first == second) {
                continue;
            }

            unionSets(first, second);
            if (findSet(1) == findSet(amountOfNodes))
            {
                maxPathDanger = edge.danger;
                break;
            }
            ++currentAddedAmount;
        }
    }

    private static void solve(final BufferedReader in) {
        readGraph(in);
        calculate();
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            out.println(maxPathDanger);
        }
    }


    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solve(in);
    }
}