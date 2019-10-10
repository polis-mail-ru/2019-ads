package ru.mail.polis.ads.part3.zvladn7;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task3 {
  private static int[] arr;

  private static void readArray(final Scanner in) {
    for (int i = 0; i < arr.length; ++i) {
      arr[i] = in.nextInt();
    }
  }

  private static int countSwap() {
    int cnt = 0;
    for (int i = 0; i < arr.length - 1; ++i) {
      for (int j = arr.length - 1; j > i; j--) {
        if (arr[j] < arr[j - 1]) {
          int tmp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = tmp;
          ++cnt;
        }
      }
    }
    return cnt;
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    arr = new int[in.nextInt()];
    readArray(in);
    out.println(countSwap());
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
