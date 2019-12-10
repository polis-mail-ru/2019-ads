package ru.mail.polis.ads.part9.KateMoreva;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

//e-olymp problem 4856 "Кратчайший путь"

public class Task4 {
    private static int max = 2000000001;

    static class Node {
        int node;
        int distance;

        int getDistance(){
            return distance;
        }

        Node(int node, int weight){
            this.node = node;
            this.distance = weight;
        }

    }

    public static void main(String[] args){
        final Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        List<List<Node>> graph = new ArrayList<>(n + 1);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));

        boolean[] used = new boolean[n + 1];
        int[] distance = new int[n + 1];
        int[] previous = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = max;
        }
        distance[s] = 0;

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();

            graph.get(b).add(new Node(e, w));
            graph.get(e).add(new Node(b, w));
        }

        priorityQueue.add(new Node(s, 0));

        while (!priorityQueue.isEmpty()) {
            int curr = priorityQueue.poll().node;
            if (used[curr]) {
                continue;
            }
            used[curr] = true;
            for (Node node :
                    graph.get(curr)) {
                int a = node.node;
                int w = node.distance;
                if (distance[a] > distance[curr] + w) {
                    distance[a] = distance[curr] + w;
                    priorityQueue.add(new Node(a, distance[a]));
                    previous[a] = curr;
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        out.printf(distance[f] + "\n");
        while (previous[f] != 0) {
            result.add(0, f);
            f = previous[f];
        }
        result.add(0, s);
        for (Integer a :
                result) {
            out.print(a + " ");
        }
        out.flush();
    }

}
