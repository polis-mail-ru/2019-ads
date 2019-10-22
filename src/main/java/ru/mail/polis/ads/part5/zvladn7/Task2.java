package ru.mail.polis.ads.part5.zvladn7;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task2 {
  private static Scanner in = new Scanner(System.in);
  private static PrintWriter out = new PrintWriter(System.out);
  private static long w;
  private static long h;
  private static long n;

  private static void solve() {
    w = in.nextLong();
    h = in.nextLong();
    n = in.nextLong();
    long left = Math.max(w, h);
    long right = n * left;
    long mid = 0;
    while (left < right) {
      mid = (left + right) / 2;
      long v = (mid / w) * (mid / h);
      if (v >= n) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    out.println(left);
  }

  public static void main(String[] args) {
    solve();
    out.close();
  }
}
