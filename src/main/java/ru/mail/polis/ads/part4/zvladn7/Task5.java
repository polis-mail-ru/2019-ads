package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task5 {
  private static long count;
  private static long weight;
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);

  private static boolean readAndCheck() throws IOException {
    String str = in.readLine();
    boolean check = true;
    long max = 0;
    long nextNumber;
    int start = 0;
    int space;
    for (int i = 0; i < count; ++i) {
      space = str.indexOf(" ", start);
      if (space == -1) {
        space = str.length();
      }
      nextNumber = Integer.parseInt(str.substring(start, space));
      start = space + 1;
      if (nextNumber < max && (nextNumber + max > weight)) {
        check = false;
        break;
      }
      if (nextNumber > max) {
        max = nextNumber;
      }
    }
    return check;
  }

  private static void solve() throws IOException {
    String str = in.readLine();
    count = Integer.parseInt(str.substring(0, str.indexOf(" ")));
    weight = Long.parseLong(str.substring(str.indexOf(" ") + 1));
    if (readAndCheck()) {
      out.print("Yes");
    } else {
      out.print("No");
    }
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
