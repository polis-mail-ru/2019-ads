package ru.mail.polis.ads.part1.zvladn7;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;

public class Task5 {
  public static void solve(final BufferedReader in, final PrintWriter out) {
    final LinkedList<Integer> queue = new LinkedList<>();
    try {
      String[] str = in.readLine().split(" ");
      while (!"exit".equals(str[0])) {
        switch (str[0]) {
          case "push": {
            queue.addLast(Integer.parseInt(str[1]));
            out.println("ok");
            break;
          }
          case "pop": {
            out.println(queue.removeFirst());
            break;
          }
          case "front": {
            out.println(queue.getFirst());
            break;
          }
          case "size": {
            out.println(queue.size());
            break;
          }
          case "clear": {
            queue.clear();
            out.println("ok");
            break;
          }
          default: {
            throw new IllegalArgumentException("I've just sent the wrong command!");
          }
        }
        str = in.readLine().split(" ");
      }
      out.println("bye");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
