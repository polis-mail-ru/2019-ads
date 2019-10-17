package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task1 {
  private static long[] arr;
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);

  private static void readArray() throws IOException {
    String str = in.readLine();
    int start = 0;
    int space = str.indexOf(" ");
    if (space == -1) {
      space = str.length();
    }
    for (int i = 1; i < arr.length; ++i) {
      arr[i] = Long.parseLong(str.substring(start, space));
      start = space + 1;
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
    }
  }

  private static boolean isNormal(int head) {
    if ((2 * head + 1) < arr.length) {
      return arr[head] <= arr[2 * head] && arr[head] <= arr[2 * head + 1];
    } else if ((2 * head) < arr.length) {
      return arr[head] <= arr[2 * head];
    } else {
      return true;
    }
  }

  private static boolean check(int head) {
    if (isNormal(head)) {
      if (2 * head + 1 < arr.length) {
        return check(2 * head) && check(2 * head + 1);
      } else if (2 * head < arr.length){
        return check(2 * head);
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  private static void solve() throws IOException{
    arr = new long[Integer.parseInt(in.readLine()) + 1];
    readArray();
    if (check(1)) {
      out.print("YES");
    } else {
      out.print("NO");
    }
  }

  public static void main(final String[] args) {
    try {
      solve();
      out.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}