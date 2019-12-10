package ru.mail.polis.ads.part9.zvladn7;

import java.io.*;
import java.util.*;

public class Task4 {

  private static final int INF = Integer.MAX_VALUE;

  private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
  private static int[] distanceTo;
  private static int[] prevForNode;
  private static boolean[] visited;
  private static int fromNode;
  private static int toNode;

  private static void readNodes(final Scanner in) {
    int nodeCount = in.nextInt();
    int linksCount = in.nextInt();
    fromNode = in.nextInt();
    toNode = in.nextInt();

    distanceTo = new int[nodeCount + 1];
    prevForNode = new int[nodeCount + 1];
    visited = new boolean[nodeCount + 1];

    for (int i = 0; i < nodeCount + 1; ++i) {
      graph.add(new ArrayList<>());
      distanceTo[i] = INF;
    }
    distanceTo[fromNode] = 0;
    for (int i = 0; i < linksCount; ++i) {
      int firstNode = in.nextInt();
      int secondNode = in.nextInt();
      int weight = in.nextInt();
      graph.get(firstNode).add(new Node(secondNode, weight));
      graph.get(secondNode).add(new Node(firstNode, weight));
    }
  }

  private static void bfs() {
    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(node -> node.weight));
    queue.add(new Node(fromNode, 0));
    while (!queue.isEmpty()) {
      int curr = queue.poll().end;
      if (visited[curr]) {
        continue;
      }
      visited[curr] = true;
      for (Node node : graph.get(curr)) {
        int dist = distanceTo[curr] + node.weight;
        if (distanceTo[node.end] > dist) {
          distanceTo[node.end] = dist;
          queue.add(new Node(node.end, distanceTo[node.end]));
          prevForNode[node.end] = curr;
        }
      }
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    readNodes(in);
    bfs();
    LinkedList<Integer> list = new LinkedList<>();
    out.println(distanceTo[toNode]);
    while (prevForNode[toNode] != 0) {
      list.addFirst(toNode);
      toNode = prevForNode[toNode];
    }
    list.addFirst(toNode);
    for (int next : list) {
      out.printf("%d ", next);
    }
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
      solve(in, out);
    }
  }

  static class Node {
    int end;
    int weight;

    Node(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
}