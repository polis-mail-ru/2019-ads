package ru.mail.polis.ads.part9.zvladn7;

import java.io.*;
import java.util.ArrayList;

public class Task2 {

  enum Color {
    WHITE,
    GRAY,
    BLACK
  }


  private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  private static Color[] nodeColor;
  private static int currentMinNode = Integer.MAX_VALUE;
  private static boolean isDetected = false;
  private static int detectedNode = -1;

  private static void readNodes(final BufferedReader in) {
    try {
      String[] next = in.readLine().split(" ");
      int nodeCount = Integer.parseInt(next[0]);
      int linksCount = Integer.parseInt(next[1]);
      nodeColor = new Color[nodeCount];
      for (int i = 0; i < nodeCount; ++i) {
        nodeColor[i] = Color.WHITE;
        graph.add(new ArrayList<>());
      }
      for (int i = 0; i < linksCount; ++i) {
        next = in.readLine().split(" ");
        int firstNode = Integer.parseInt(next[0]);
        int secondNode = Integer.parseInt(next[1]);
        graph.get(firstNode - 1).add(secondNode);
        graph.get(secondNode - 1).add(firstNode);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private static void dfs(int node, int prev) {
    nodeColor[node] = Color.GRAY;
    for (int currentNode : graph.get(node)) {
      if (prev == currentNode - 1) {
        continue;
      }
      if (nodeColor[currentNode - 1] == Color.WHITE) {
        dfs(currentNode - 1, node);
      } else if (nodeColor[currentNode - 1] == Color.GRAY) {
        isDetected = true;
        detectedNode = currentNode - 1;
        if (currentMinNode > currentNode - 1) {
          currentMinNode = currentNode - 1;
        }
      }
    }
    nodeColor[node] = Color.BLACK;
    if (isDetected) {
      if (detectedNode == node) {
        isDetected = false;
      } else if (currentMinNode > node) {
        currentMinNode = node;
      }
    }
  }

  private static void doDfs() {
    for (int i = 0; i < graph.size(); ++i) {
      if (nodeColor[i] == Color.WHITE) {
        dfs(i, -1);
      }
    }
  }

  private static void solve(final BufferedReader in) {
    readNodes(in);
    doDfs();
    if (currentMinNode == Integer.MAX_VALUE) {
      System.out.printf("No");
    } else {
      System.out.printf("Yes\n%d", currentMinNode + 1);
    }
  }

  public static void main(final String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    solve(in);
  }
}
