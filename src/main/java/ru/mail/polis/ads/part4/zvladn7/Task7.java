package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task7 {
  private static long arr[];
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static int cows;

  private static void readArray() throws IOException {
    String str = in.readLine();
    int start = 0;
    int space;
    for (int i = 0; i < arr.length; ++i) {
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
      arr[i] = Long.parseLong(str.substring(start, space));
      start = space + 1;
    }
  }

  private static long findMaxMin() {
    long lftLimit = 0;
    long rhtLimit = arr[arr.length - 1] - arr[0];
    long mid;
    while (lftLimit != rhtLimit) {
      mid = (lftLimit + rhtLimit) / 2;
      int cnt = 0;
      int curr = 0;
      for (int i = 1; i < arr.length; ++i) {
        if (arr[i] - arr[curr] > mid) {
          ++cnt;
          curr = i;
        }
      }
      if (cnt < cows - 1) {
        rhtLimit = mid;
      } else {
        lftLimit = mid + 1;
      }
    }
    return rhtLimit;
  }

  private static void solve() throws IOException {
    String str = in.readLine();
    arr = new long[Integer.parseInt(str.substring(0, str.indexOf(" ")))];
    cows = Integer.parseInt(str.substring(str.indexOf(" ") + 1));
    readArray();
    out.println(findMaxMin());
  }

  public static void main(String[] args) {
    try {
      solve();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
