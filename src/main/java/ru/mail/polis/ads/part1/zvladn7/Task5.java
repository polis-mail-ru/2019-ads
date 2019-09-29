package ru.mail.polis.ads.part1.zvladn7;
import java.io.*;
import java.util.LinkedList;

public class Task5 {
  public static void solve(BufferedReader in, PrintWriter out) {
    LinkedList<Integer> queue = new LinkedList<>();
    try {
      String[] str = in.readLine().split(" ");
      out:
      while (true) {
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
          case "exit": {
            out.println("bye");
            break out;
          }
        }
        str = in.readLine().split(" ");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
