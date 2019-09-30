package ru.mail.polis.ads.part1.zvladn7;

import java.io.*;

public class Task2 {
  public static int[][] lenCorrectSeq;
  public static int[][] insrtPos;
  public static char[] chars;
  public static int calc(int begin, int end) {
    if (begin == end) {
      return 1;
    }
    if (begin > end) {
      return 0;
    }
    if (lenCorrectSeq[begin][end] != Integer.MAX_VALUE) {
      return lenCorrectSeq[begin][end];
    }
    if ((chars[begin] == '(' && chars[end] == ')') || (chars[begin] == '[' && chars[end] == ']')) {
      lenCorrectSeq[begin][end] = calc(begin + 1, end - 1);
    }
    for (int k = begin; k < end; ++k) {
      int tmpCorrectSeqLen = calc(begin, k) + calc(k + 1, end);
      if (tmpCorrectSeqLen < lenCorrectSeq[begin][end]) {
        insrtPos[begin][end] = k;
        lenCorrectSeq[begin][end] = tmpCorrectSeqLen;
      }
    }
    return lenCorrectSeq[begin][end];
  }

  public static void printResult(int begin, int end, PrintWriter out) {
    if (begin > end) {
      return;
    }
    if (begin == end) {
      if (chars[begin] == '(' || chars[end] == ')') {
        out.print("()");
      } else {
        out.print("[]");
      }
    } else if (insrtPos[begin][end] == -1) {
      out.print(chars[begin]);
      printResult(begin + 1, end - 1, out);
      out.print(chars[end]);
    } else {
      printResult(begin, insrtPos[begin][end], out);
      printResult(insrtPos[begin][end] + 1, end, out);
    }
  }

  public static void initArraysByDefaults() {
    for (int i = 0; i < insrtPos.length; ++i) {
      for (int j = 0; j < insrtPos.length; ++j) {
        insrtPos[i][j] = -1;
      }
    }
    for (int i = 0; i < insrtPos.length; ++i) {
      for (int j = 0; j < insrtPos.length; ++j) {
        lenCorrectSeq[i][j] = Integer.MAX_VALUE;
      }
    }
  }


  public static void solve(BufferedReader in, PrintWriter out) {
    try {
      chars = in.readLine().toCharArray();
      lenCorrectSeq = new int[chars.length][chars.length];
      insrtPos = new int[chars.length][chars.length];
      initArraysByDefaults();
      calc(0, chars.length - 1);
      printResult(0, chars.length - 1, out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
