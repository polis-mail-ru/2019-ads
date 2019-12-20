package ru.mail.polis.ads.part10.zvladn7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Task2 {

    private static class Edge implements Comparable<Edge> {
        int firstNode;
        int secondNode;
        int weight;

        Edge(int firstNode, int secondNode, int weight) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }
    }

    private static int amountOfNodes;
    private static PriorityQueue<Edge> priorityEdges = new PriorityQueue<>();
    private static int minTreeWeight;
    private static int[] parent;

    private static int findSet(int v) {
        if (v == parent[v]) {
            return v;
        }

        return parent[v] = findSet(parent[v]);
    }

    private static void unionSets(int first, int second) {
        first = findSet(first);
        second = findSet(second);

        if (first != second) {
            parent[second] = first;
        }
    }

    private static void readGraph(final BufferedReader in) {
        try {
            String[] next = in.readLine().split(" ");
            amountOfNodes = Integer.parseInt(next[0]);
            int amountOfEdges = Integer.parseInt(next[1]);

            parent = new int[amountOfNodes + 1];
            for (int i = 1; i < parent.length; ++i) {
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

    private static void calculateMinTree() {
        int currentAddedAmount = 1;

        while (currentAddedAmount < amountOfNodes) {
            Edge edge = priorityEdges.poll();
            if (findSet(edge.firstNode) == findSet(edge.secondNode)) {
                continue;
            }

            unionSets(edge.firstNode, edge.secondNode);
            ++currentAddedAmount;
            minTreeWeight += edge.weight;
        }
    }

    private static void solve(final BufferedReader in) {
        readGraph(in);
        calculateMinTree();
        System.out.println(minTreeWeight);
    }


    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solve(in);
    }
}