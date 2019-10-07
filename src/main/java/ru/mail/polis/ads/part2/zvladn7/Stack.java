package ru.mail.polis.ads.part2.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public final class Stack {
  private static LinkedList<Integer> list = new LinkedList<>();

  private static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      String[] str = {""};
      StringBuilder sb = new StringBuilder();
      while (!"exit".equals(str[0])) {
        str = in.readLine().split(" ");
        switch (str[0]) {
          case "push": {
            list.addLast(Integer.parseInt(str[1]));
            sb.append("ok\n");
            break;
          }
          case "pop": {
            if (list.isEmpty()) {
              sb.append("error\n");
            } else {
              sb.append(list.removeLast()).append("\n");
            }
            break;
          }
          case "back": {
            if (list.isEmpty()) {
              sb.append("error\n");
            } else {
              sb.append(list.getLast()).append("\n");
            }
            break;
          }
          case "size": {
            sb.append(list.size()).append("\n");
            break;
          }
          case "clear": {
            list.clear();
            sb.append("ok\n");
          }
        }
      }
      sb.append("bye");
      out.println(sb);
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
