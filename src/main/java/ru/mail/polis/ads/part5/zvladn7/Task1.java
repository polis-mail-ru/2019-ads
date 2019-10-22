package ru.mail.polis.ads.part5.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static java.lang.Math.sqrt;

public class Task1 {
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static double c;
  public static final double EPSILON = 0.000001;

  private static double fun(final double x) {
    return x * x + sqrt(x);
  }

  private static void solve() throws IOException {
    c = Double.parseDouble(in.readLine());
    double left = 0;
    double right = c;
    double mid = 0.0;
    double y;
    do {
      mid = (right + left) / 2;
      y = fun(mid);
      if (y > c) {
        right = mid;
      } else {
        left = mid;
      }
    } while (Math.abs(y - c) > EPSILON);
    out.print(String.format("%.6f", mid));
  }

  public static void main(String[] args) {
    try {
      solve();
      out.close();
    } catch (Exception e) {

    }
  }
}
