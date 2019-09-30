package ru.mail.polis.ads.part1.zvladn7;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;


public final class Task1 {
  private Task1() {
  }

  private static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      final String[] separateNum = in.readLine().split("");
      for (final String s : separateNum) {
        out.print(s + " ");
      }
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
