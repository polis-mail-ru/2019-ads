package ru.mail.polis.ads.part5.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task4 {
  private static boolean[][] d;
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static String w;
  private static String p;


  private static void solve() throws IOException {
    String s1 = in.readLine();
    String s2 = in.readLine();
    if (s1.contains("*") || s1.contains("?")) {
      w = s2;
      p = s1;
    } else {
      w = s1;
      p = s2;
    }
    int n = w.length();
    int m = p.length();
    d = new boolean[n + 1][m + 1];
    d[0][0] = true;
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (w.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
          d[i][j] = d[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
        } else {
          d[i][j] = false;
        }
      }
    }
    if (d[w.length()][p.length()]) {
      out.print("YES");
    } else {
      out.print("NO");
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
