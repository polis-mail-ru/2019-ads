package ru.mail.polis.ads.part5.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task3 {
  private static long[] numbers;
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static int[] maxCount;

  private static void readArray() throws IOException {
    String str = in.readLine();
    int start = 0;
    int space;
    for (int i = 0; i < numbers.length; ++i) {
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
      numbers[i] = Integer.parseInt(str.substring(start, space));
      start = space + 1;
    }
  }

  private static void calc() {
    for (int i = 1; i < numbers.length; ++i) {
      int max = 0;
      for (int j = 0; j < i; ++j) {
        if (numbers[j] != 0 && numbers[i] % numbers[j] == 0 && maxCount[j] > max) {
          max = maxCount[j];
        }
      }
      maxCount[i] = max + 1;
    }
  }

  private static int findMax() {
    int max = maxCount[0];
    for (int i = 1; i < numbers.length; ++i) {
      if (maxCount[i] > max) {
        max = maxCount[i];
      }
    }
    return max;
  }

  private static void solve() throws IOException {
    numbers = new long[Integer.parseInt(in.readLine())];
    maxCount = new int[numbers.length];
    readArray();
    maxCount[0] = 1;
    calc();
    System.out.println(findMax());
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
