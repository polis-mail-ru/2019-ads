package ru.mail.polis.ads.part1.zvladn7;
import java.io.*;

public class Task3 {
  static char[] chars;
  static String rowString;
  static String theShortestString;
  static String[][] boxed;

  public static String minLenString(String str1, String str2) {
    return str1.length() < str2.length() ? str1 : str2;
  }

  public static boolean isRepeated(int left, int right, int lenPeriod) {
    boolean isRepeatedly = true;
    for (int i = left + lenPeriod; i <= right; ++i) {
      if (chars[i - lenPeriod] != chars[i]) {
        isRepeatedly = false;
        break;
      }
    }
    return isRepeatedly;
  }

  public static void partlyBoxing(int left, int right) {
    for (int rightBorder = left; rightBorder < right; ++rightBorder) {
      int leftBorder = rightBorder + 1;
      String tempString = boxed[left][rightBorder] + boxed[leftBorder][right];
      theShortestString = minLenString(tempString, theShortestString);
    }
  }

  public static void checkPeriodic(int left, int right, int length) {
    for (int lenPeriod = 1; lenPeriod < length; ++lenPeriod) {
      if (length % lenPeriod == 0) {
        if (isRepeated(left, right, lenPeriod)) {
          String tempString = length / lenPeriod + "(" + boxed[left][left + lenPeriod - 1] + ")";
          theShortestString = minLenString(tempString, theShortestString);
        }
      }
    }
  }

  public static void findTheShortest(int left, int right, int length) {
    partlyBoxing(left, right);
    checkPeriodic(left, right, length);
  }


  public static void boxing() {
    for (int length = 1; length <= chars.length; ++length) {
      for (int left = 0; length + left - 1 < chars.length; ++left) {
        int right = length + left - 1;
        theShortestString = rowString.substring(left, left + length);
        if (length > 4) {
          findTheShortest(left, right, length);
        }
        boxed[left][right] = theShortestString;
      }
    }
  }



  public static void solve(BufferedReader in, PrintWriter out) {
    try {
      rowString = in.readLine();
      chars = rowString.toCharArray();
      boxed = new String[chars.length][chars.length];
      boxing();
      out.println(boxed[0][chars.length - 1]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
