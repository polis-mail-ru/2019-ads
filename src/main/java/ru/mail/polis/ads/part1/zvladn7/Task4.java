package ru.mail.polis.ads.part1.zvladn7;

import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.util.Scanner;

public final class Task4 {
  private static int[][] maxLen;
  private static int[] arr1;
  private static int[] arr2;

  private Task4() {
  }

  private static int calc(final int i, final int j) {
    if (i == 0 || j == 0) {
      return 0;
    }
    if (maxLen[i][j] != Integer.MAX_VALUE) {
      return maxLen[i][j];
    }
    if (arr1[i] == arr2[j]) {
      maxLen[i][j] = 1 + calc(i - 1, j - 1);
      return maxLen[i][j];
    } else {
      maxLen[i][j] = Math.max(calc(i - 1, j), calc(i, j - 1));
      return maxLen[i][j];
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {

    final int size1 = in.nextInt();
    arr1 = new int[size1 + 1];
    for (int i = 1; i <= size1; ++i) {
      arr1[i] = in.nextInt();
    }
    final int size2 = in.nextInt();
    arr2 = new int[size2 + 1];
    for (int i = 1; i <= size2; ++i) {
      arr2[i] = in.nextInt();
    }

    maxLen = new int[size1 + 1][size2 + 1];
    for (int i = 0; i < maxLen.length; ++i) {
      for (int j = 0; j < maxLen[i].length; ++j) {
        maxLen[i][j] = Integer.MAX_VALUE;
      }
    }

    out.println(calc(size1, size2));
  }

  public static void main(final String[] args) {
    final Scanner in = new Scanner(new BufferedInputStream(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}

