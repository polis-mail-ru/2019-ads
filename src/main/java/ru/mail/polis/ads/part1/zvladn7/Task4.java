package ru.mail.polis.ads.part1.zvladn7;
import java.io.*;
import java.util.Scanner;

public class Task4 {
  static int[][] maxLen;
  static int[] arr1;
  static int[] arr2;

  public static int calc(int i, int j) {
    if (i == 0 || j == 0) {
      return 0;
    }
    if (maxLen[i][j] != Integer.MAX_VALUE) {
      return maxLen[i][j];
    }
    if (arr1[i] == arr2[j]) {
      return maxLen[i][j] = 1 + calc(i - 1, j - 1);
    } else {
      return maxLen[i][j] = Math.max(calc(i - 1, j), calc(i, j - 1));
    }
  }

  public static void solve(Scanner in, PrintWriter out) {

    int size1 = in.nextInt();
    arr1 = new int[size1 + 1];
    for (int i = 1; i <= size1; ++i) {
      arr1[i] = in.nextInt();
    }
    int size2 = in.nextInt();
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

  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedInputStream(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}

