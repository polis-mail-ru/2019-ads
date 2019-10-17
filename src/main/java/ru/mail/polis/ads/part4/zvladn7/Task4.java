package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task4 {
  private static int[] arr;
  private static int[] helpArr;
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static int count = 0;

  private static void readArray() throws IOException {
    String str = in.readLine();
    int start = 0;
    int space = str.indexOf(" ");
    for (int i = 0; i < arr.length; ++i) {
      arr[i] = Integer.parseInt(str.substring(start, space));
      start = space + 1;
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
    }
  }

  private static void merge(int left, final int mid, final int right) {
    int startSecond = mid + 1;
    int i = left;
    while ((left <= mid) && (startSecond <= right)) {
      if (helpArr[left] < helpArr[startSecond]) {
        arr[i] = helpArr[left++];
      } else {
        count += mid - left + 1;
        arr[i] = helpArr[startSecond++];
      }
      ++i;
    }

    while (left <= mid) {
      arr[i++] = helpArr[left++];
    }

    while (startSecond <= right) {
      arr[i++] = helpArr[startSecond++];
    }
  }

  private static void mergeSort(final int left, final int right) {
    if (left != right) {
      final int mid = (left + right) / 2;
      mergeSort(left, mid);
      mergeSort(mid + 1, right);
      for (int i = left; i <= right; ++i) {
        helpArr[i] = arr[i];
      }
      merge(left, mid, right);
    }
  }

  private static void solve() throws IOException {
    arr = new int[Integer.parseInt(in.readLine())];
    helpArr = new int[arr.length];
    readArray();
    mergeSort(0, arr.length - 1);
    out.println(count);
  }

  public static void main(final String[] args) {
    try {
      solve();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
