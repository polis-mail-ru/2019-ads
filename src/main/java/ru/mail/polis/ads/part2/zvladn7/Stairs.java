package ru.mail.polis.ads.part2.zvladn7;

import java.io.PrintWriter;
import java.util.Scanner;

public class Stairs {
  private static int[] points;
  private static int[] maxSumPoints;
  private static int maxStep;

  private static void readPointsArray(final Scanner in) {
    for (int i = 0; i < points.length - 1; ++i) {
      points[i] = in.nextInt();
    }
  }

  private static void calcMaxPoints() {
    for (int i = 1; i < maxSumPoints.length; ++i) {
      int currMax = Integer.MIN_VALUE;
      for (int j = i - 1; j >= i - maxStep; --j) {
        if (j < 0) {
          break;
        }
        if (maxSumPoints[j] > currMax) {
          currMax = maxSumPoints[j];
        }
      }
      maxSumPoints[i] = currMax + points[i - 1];
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    points = new int[in.nextInt() + 1];
    maxSumPoints = new int[points.length + 1];
    readPointsArray(in);
    maxStep = in.nextInt();
    calcMaxPoints();
    out.print(maxSumPoints[maxSumPoints.length - 1]);
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in , out);
    }
  }
}