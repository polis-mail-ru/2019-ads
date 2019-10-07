package ru.mail.polis.ads.part2.zvladn7;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Mouse {
  private static int[][] granules;
  private static int[][] maxCurrentGranules;
  private static String[][] path;
  private static int rows;
  private static int columns;

  private static void readGranulesAmount(final Scanner in) {
    for (int i = rows-1; i >= 0; --i) {
      for (int j = 0; j < columns; ++j) {
        granules[i][j] = in.nextInt();
      }
    }
  }

  private static void initStarterMaxValues() {
    maxCurrentGranules[0][0] = granules[0][0];
    path[0][0] = "";
    for (int i = 1; i < columns; ++i) {
      maxCurrentGranules[0][i] = maxCurrentGranules[0][i - 1] + granules[0][i];
      path[0][i] = path[0][i - 1] + "R";
    }
    for (int i = 1; i < rows; ++i) {
      maxCurrentGranules[i][0] = maxCurrentGranules[i - 1][0] + granules[i][0];
      path[i][0] = path[i - 1][0] + "F";
    }
  }

  private static void calculateMaxValue() {
    for (int i = 1; i < rows; ++i) {
      for (int j = 1; j < columns; ++j) {
        if (maxCurrentGranules[i][j - 1] > maxCurrentGranules[i - 1][j]) {
          maxCurrentGranules[i][j] = maxCurrentGranules[i][j - 1] + granules[i][j];
          path[i][j] = path[i][j - 1] + "R";
        } else {
          maxCurrentGranules[i][j] = maxCurrentGranules[i - 1][j] + granules[i][j];
          path[i][j] = path[i - 1][j] + "F";
        }
      }
    }
  }

  private static void solve(final Scanner in, final PrintWriter out) {
    rows = in.nextInt();
    columns = in.nextInt();
    granules = new int[rows][columns];
    maxCurrentGranules = new int[rows][columns];
    path = new String[rows][columns];
    readGranulesAmount(in);
    initStarterMaxValues();
    calculateMaxValue();
    out.println(path[rows - 1][columns - 1]);
  }

  public static void main(final String[] args) {
    Scanner in = new Scanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in,out);
    }
  }
}