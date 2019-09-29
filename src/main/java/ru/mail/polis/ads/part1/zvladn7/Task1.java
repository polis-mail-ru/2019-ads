package ru.mail.polis.ads.part1.zvladn7;
import java.io.*;

public class Task1 {

  public static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      String[] separateNum = in.readLine().split("");
      StringBuilder str = new StringBuilder();
      for (String s : separateNum) {
        str.append(s).append(" ");
      }
      out.print(str);
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
