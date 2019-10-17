package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Task6 {
  private static int[] sortedArray;
  private static int amount;
  private static PrintWriter out = new PrintWriter(System.out);
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

  private static void solve() throws IOException {
    String str = in.readLine();
    sortedArray = new int[Integer.parseInt(str.substring(0, str.indexOf(" ")))];
    amount = Integer.parseInt(str.substring(str.indexOf(" ") + 1, str.length()));
    str = in.readLine();
    int start = 0;
    int space = str.indexOf(" ");
    for (int i = 0; i < sortedArray.length; ++i) {
      sortedArray[i] = Integer.parseInt(str.substring(start, space));
      start = space + 1;
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
    }
    for (int i = 0; i < amount; ++i) {
      int number = Integer.parseInt(in.readLine());
      int begin = 0;
      int end = sortedArray.length - 1;
      int mid;
      boolean found = false;
      while (begin <= end) {
        mid = (begin + end) / 2;
        if (number == sortedArray[mid]) {
          found = true;
          break;
        } else if (number > sortedArray[mid]) {
          begin = mid + 1;
        } else {
          end = mid - 1;
        }
      }
      if (found) {
        out.println("YES");
      } else {
        out.println("NO");
      }
    }
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
