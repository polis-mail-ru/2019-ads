package ru.mail.polis.ads.part3.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class Task1 {
  private static int[] arr;
  private static int[] helpArr;

  private static void merge(int left, final int middle, final int right) {
    int startSecond = middle + 1;
    int i = left;
    while ((left <= middle) && (startSecond <= right)) {
      if (helpArr[left] < helpArr[startSecond]) {
        arr[i] = helpArr[left++];
      } else {
        arr[i] = helpArr[startSecond++];
      }
      ++i;
    }
    while (left <= middle) {
      arr[i++] = helpArr[left++];
    }
    while (startSecond <= right) {
      arr[i++] = helpArr[startSecond++];
    }
  }

  private static void mergeSort(final int left, final int right) {
    if (left != right) {
      final int middle = (left + right) / 2;
      mergeSort(left, middle);
      mergeSort(middle + 1, right);
      for (int i = left; i <= right; ++i) {
        helpArr[i] = arr[i];
      }
      merge(left, middle, right);
    }
  }

  private static void readArray(final BufferedReader in) throws IOException{
    String[] tmp = in.readLine().split(" ");
    for (int i = 0; i < arr.length; ++i) {
      arr[i] = Integer.parseInt(tmp[i]);
    }
  }

  private static void printArray(final PrintWriter out) {
    for (int el : arr) {
      out.print(el + " ");
    }
  }

  private static void solve(final BufferedReader in, final PrintWriter out) {
    try {
      arr = new int[Integer.parseInt(in.readLine())];
      helpArr = new int[arr.length];
      readArray(in);
      mergeSort(0, arr.length - 1);
      printArray(out);
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
