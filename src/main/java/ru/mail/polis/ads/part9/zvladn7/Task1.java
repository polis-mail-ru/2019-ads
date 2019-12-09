package ru.mail.polis.ads.part9.zvladn7;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {

  enum Color {
    WHITE,
    GRAY,
    BLACK
  }

  private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  private static Color[] nodeColor;
  private static int[] answer;
  private static int count = 0;
  private static boolean checker = false;

  private static void readNode(final BufferedReader in) {
    try {
      String[] next = in.readLine().split(" ");
      int nodeCount = Integer.parseInt(next[0]);
      int countOfLinks = Integer.parseInt(next[1]);
      for (int i = 0; i < nodeCount; ++i) {
        graph.add(new ArrayList<>());
      }
      for (int i = 0; i < countOfLinks; ++i) {
        next = in.readLine().split(" ");
        int nodeFrom = Integer.parseInt(next[0]);
        Integer nodeTo = Integer.parseInt(next[1]);
        graph.get(nodeFrom - 1).add(nodeTo);
      }
      answer = new int[nodeCount];
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private static void dfs(int node) {
    nodeColor[node] = Color.GRAY;
    for (int currentNode : graph.get(node)) {
      if (nodeColor[currentNode - 1] == Color.WHITE) {
        dfs(currentNode - 1);
      } else if (nodeColor[currentNode - 1] == Color.GRAY) {
        checker = true;
        return;
      }
    }
    nodeColor[node] = Color.BLACK;
    answer[count++] = node + 1;
  }

  private static void sort() {
    for (int i = 0; i < graph.size(); ++i) {
      if (nodeColor[i] == Color.WHITE) {
        dfs(i);
      }
    }
  }



  private static void solve(final BufferedReader in, final PrintWriter out) {
    readNode(in);
    nodeColor = new Color[graph.size()];
    for (int i = 0; i < nodeColor.length; ++i) {
      nodeColor[i] = Color.WHITE;
    }
    sort();
    if (checker) {
      out.println("-1");
    } else {
      for (int i = answer.length - 1; i >= 0; --i) {
        out.printf("%d ", answer[i]);
      }
    }
  }



  public static void main(final String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
