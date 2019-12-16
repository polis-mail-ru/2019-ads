package ru.mail.polis.ads.part9.zvladn7;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Task2 {

  enum Color {
    WHITE,
    GRAY,
    BLACK
  }

  private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  private static Color[] nodeColor;
  private static int[] prev;
  private static boolean[] inCicle;

  private static void doDfs() {
    for (int i = 1; i < graph.size(); ++i) {
      if (nodeColor[i] == Color.WHITE) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(i);

        while (!stack.isEmpty()) {
          int curr = stack.getLast();
          nodeColor[curr] = Color.GRAY;
          boolean check = true;

          for (int currentNode : graph.get(curr)) {
            if (prev[curr] == currentNode) {
              continue;
            }
            if (nodeColor[currentNode] == Color.WHITE) {
              prev[currentNode] = curr;
              stack.addLast(currentNode);

              check = false;
              break;
            } else if (nodeColor[currentNode] == Color.GRAY) {
              inCicle[currentNode] = true;

              int forNode = curr;
              if (inCicle[forNode]) {
                continue;
              }

              while (forNode != currentNode) {
                inCicle[forNode] = true;
                forNode = prev[forNode];
                if (inCicle[forNode]) {
                  break;
                }
              }

            }
          }
          if (check) {
            stack.removeLast();
            nodeColor[curr] = Color.BLACK;
          }
        }
      }
    }
  }

  public static void main(final String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try {
      String[] next = in.readLine().split(" ");
      int nodeCount = Integer.parseInt(next[0]);
      int linksCount = Integer.parseInt(next[1]);
      nodeColor = new Color[nodeCount + 1];
      inCicle = new boolean[nodeCount + 1];
      prev = new int[nodeCount + 1];
      for (int i = 0; i < nodeColor.length; ++i) {
        nodeColor[i] = Color.WHITE;
        graph.add(new ArrayList<>());
      }
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
    doDfs();
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < inCicle.length; ++i) {
      if (inCicle[i] && min > i) {
        min = i;
      }
    }
    if (min == Integer.MAX_VALUE) {
      System.out.printf("No");
    } else {
      System.out.printf("Yes\n%d", min);
    }
  }
}
