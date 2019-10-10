package ru.mail.polis.ads.part3.zvladn7;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task2 {
  private static int[] arr;

  private static void sort() {
    for (int i = 0; i < arr.length - 1; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; ++j) {
        if (compare(j, minIndex)) {
          minIndex = j;
        }
      }
      int tmp = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = tmp;
      arr[minIndex] = tmp;
    }
  }

  private static boolean compare(int j, int minIndex) {
    if (arr[j] % 10 == arr[minIndex] % 10) {
      return arr[j] < arr[minIndex];
    } else {
      return arr[j] % 10 < arr[minIndex] % 10;
    }
  }

  private static void readArray(final Scanner in) {
    for (int i = 0; i < arr.length; ++i) {
      arr[i] = in.nextInt();
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    arr = new int[in.nextInt()];
    readArray(in);
    sort();
    for (int el : arr) {
      out.print(el + " ");
    }
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
