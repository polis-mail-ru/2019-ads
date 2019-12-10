package ru.mail.polis.ads.part9.zvladn7;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Task5 {

  private static final int INF = Integer.MAX_VALUE;

  private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  private static int[] distanceTo;
  private static int[] prevForNode;
  private static boolean[] visited;
  private static int fromNode;
  private static int toNode;

  private static void readNodes(final BufferedReader in) {
    try {
      String[] next = in.readLine().split(" ");
      int nodeCount = Integer.parseInt(next[0]);
      distanceTo = new int[nodeCount + 1];
      prevForNode = new int[nodeCount + 1];
      visited = new boolean[nodeCount + 1];
      int linksCount = Integer.parseInt(next[1]);
      next = in.readLine().split(" ");
      fromNode = Integer.parseInt(next[0]);
      toNode = Integer.parseInt(next[1]);
      for (int i = 0; i < nodeCount + 1; ++i) {
        graph.add(new ArrayList<>());
        distanceTo[i] = INF;
      }
      visited[fromNode] = true;
      distanceTo[fromNode] = 0;
      for (int i = 0; i < linksCount; ++i) {
        next = in.readLine().split(" ");
        int firstNode = Integer.parseInt(next[0]);
        int secondNode = Integer.parseInt(next[1]);
        graph.get(firstNode).add(secondNode);
        graph.get(secondNode).add(firstNode);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private static void bfs() {
    LinkedList<Integer> queue = new LinkedList<>();
    queue.addLast(fromNode);
    while (!queue.isEmpty()) {
      Integer curr = queue.removeFirst();
      for (Integer node : graph.get(curr)) {
        if (visited[node]) {
          continue;
        }
        visited[node] = true;
        int dist = distanceTo[curr] + 1;
        if (distanceTo[node] > dist) {
          distanceTo[node] = dist;
          queue.addLast(node);
          prevForNode[node] = curr;
        }
      }
    }
  }

  private static void solve(final BufferedReader in, final PrintWriter out) {
    readNodes(in);
    bfs();
    if (distanceTo[toNode] == INF) {
      out.println(-1);
    } else {
      out.println(distanceTo[toNode]);
      StringBuilder sb = new StringBuilder();
      while (prevForNode[toNode] != 0) {
        sb.append(toNode).append(" ");
        toNode = prevForNode[toNode];
      }
      sb.append(toNode);
      out.println(sb.reverse());
    }
  }

  public static void main(final String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
