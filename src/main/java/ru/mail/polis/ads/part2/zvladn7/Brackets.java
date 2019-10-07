package ru.mail.polis.ads.part2.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public final class Brackets {
  private static boolean check = true;
  private static char[] chars;
  private static LinkedList<Character> list= new LinkedList<>();

  private static void closeBracket(final char checkingBracket) {
    if (!list.isEmpty() && list.getLast() == checkingBracket) {
      list.removeLast();
    } else {
      check = false;
    }
  }

  private static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      chars = in.readLine().toCharArray();
      for (int i = 0; i < chars.length; ++i) {
        switch (chars[i]) {
          case '(':
          case '[': {
            list.addLast(chars[i]);
            break;
          }
          case ')': {
            closeBracket('(');
            break;
          }
          case ']': {
            closeBracket('[');
            break;
          }
          default:
        }
      }
      if (check && list.isEmpty()) {
        out.print("YES");
      } else {
        out.print("NO");
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