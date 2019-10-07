package ru.mail.polis.ads.part2.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public final class Expressions {
  private static LinkedList<TreeNode> list = new LinkedList<>();
  private static char[] chars;
  private static StringBuilder sb = new StringBuilder();
  private static TreeNode head;

  private static class TreeNode {
    TreeNode left;
    TreeNode right;
    char node;

    TreeNode(final TreeNode left, final TreeNode right, final char node) {
      this.left = left;
      this.right = right;
      this.node = node;
    }
  }

  private static String toDecodeByQueue(final TreeNode head) {
    list.clear();
    list.addLast(head);
    sb = new StringBuilder();
    while (!list.isEmpty()) {
      final TreeNode tmpNode = list.removeFirst();
      sb.append(tmpNode.node);
      if (tmpNode.left != null && tmpNode.right != null) {
        list.addLast(tmpNode.left);
        list.addLast(tmpNode.right);
      }
    }
    return (new StringBuffer(sb)).reverse().toString();
  }

  private static TreeNode toInfixByStack() {
    for (int i = 0; i < chars.length; ++i) {
      if (Character.isUpperCase(chars[i])) {
        final TreeNode right = list.removeLast();
        final TreeNode left = list.removeLast();
        list.addLast(new TreeNode(left, right, chars[i]));
        sb = new StringBuilder();
      } else {
        list.addLast(new TreeNode(null, null, chars[i]));
      }
    }
    return list.getLast();
  }


  private static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      int amount = Integer.parseInt(in.readLine());
      for (int i = 0; i < amount; ++i) {
        final String str = in.readLine();
        chars = str.toCharArray();
        head = toInfixByStack();
        out.println(toDecodeByQueue(head));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}