package ru.mail.polis.ads.part5.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Task5 {
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static StringBuilder sb;
  private static int n;
  private static int[] a;
  private static String[] rearrangement;

  private static void rearrange() {
   for (int i = 0; i < rearrangement.length; ++i) {
     int j;
     sb = new StringBuilder();
     for (int el: a) {
       sb.append(el).append(" ");
     }
     rearrangement[i] = sb.toString();
     for (j = a.length - 1; j > 1; --j) {
       if (a[j - 1] < a[j]) {
         break;
       }
     }
     int maxIndex = j;
     for (int k = j; k < a.length; ++k) {
       if (a[k] > a[j - 1]) {
         maxIndex = k;
       }
     }
     swap(j - 1, maxIndex);
     Arrays.sort(a, j, a.length);
   }
  }

  private static void swap(final int i, final int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static void solve() throws IOException {
    n = Integer.parseInt(in.readLine());
    a = new int[n];
    int x = 1;
    for (int i = 1; i <=n; ++i) {
      x *= i;
    }
    rearrangement = new String[x];
    for (int i = 1; i <= a.length; ++i) {
      a[i - 1] = i;
    }
    if (n != 1) {
      rearrange();
      for (String el : rearrangement) {
        out.println(el);
      }
    } else {
      out.print(1);
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
