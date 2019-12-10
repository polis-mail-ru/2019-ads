package ru.mail.polis.ads.part9.zvladn7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task3 {
  static class Edge {
    int begin;
    int end;
    int weight;

    Edge() {

    }
  }

  private static Edge[] graph;
  private static int[] minDistance;

  private static void readEdges(final Scanner in) {
      int nodesAmount = in.nextInt();
      minDistance = new int[nodesAmount + 1];
      minDistance[1] = 0;
      for (int i = 2; i < minDistance.length; ++i) {
        minDistance[i] = 30000;
      }
      int edgesAmount = in.nextInt();
      graph = new Edge[edgesAmount];
      for (int i = 0; i < graph.length; ++i) {
        graph[i] = new Edge();
        graph[i].begin = in.nextInt();
        graph[i].end = in.nextInt();
        graph[i].weight = in.nextInt();
      }
  }

  private static void findShortestPaths() {
    for (int i = 1; i < minDistance.length; ++i) {
      for (Edge next : graph) {
        if (minDistance[next.begin] == 30000) {
          continue;
        }
        minDistance[next.end] = Math.min(minDistance[next.end], minDistance[next.begin] + next.weight);
      }
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    readEdges(in);
    findShortestPaths();
    for (int i = 1; i < minDistance.length; ++i) {
      out.printf("%d ", minDistance[i]);
    }
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
