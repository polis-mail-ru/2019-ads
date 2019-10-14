package ru.mail.polis.ads.part.first;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5854174
 */
public class TaskTwo {

    private static int[][] d;

    private static void solve(final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        d = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            Arrays.fill(d[i], -1);
        }
        out.println(seqCreator(0, str.length() - 1, str));
    }

    private static String seqCreator(int l, int j, String str) {
        if (l >= j + 1) { return ""; }
        switch (str.charAt(l)) {
            case ']':
                return "[]" + seqCreator(l + 1, j, str);
            case ')':
                return "()" + seqCreator(l + 1, j, str);
        }
        switch (str.charAt(j)) {
            case '[':
                return seqCreator(l, j - 1, str) + "[]";
            case '(':
                return seqCreator(l, j - 1, str) + "()";
        }
        int ind = partitionIndex(l, j, str);
        if (ind == -10) {
            return str.charAt(l) + seqCreator(l + 1, j - 1, str) + str.charAt(j);
        } else {
            return seqCreator(l, ind, str) + seqCreator(ind + 1, j, str);
        }
    }

    private static int partitionIndex(int l, int j, String str) {
        int minIndex = l;
        int min = 2 + minLen(l + 1, j, str);
        int sum = 2 + minLen(l, j - 1, str);
        if (min > sum) {
            min = sum;
            minIndex = j - 1;
        }
        for (int i = l + 1; i < j - 1; i++) {
            sum = minLen(l, i, str) + minLen(i + 1, j, str);
            if (min > sum) {
                min = sum;
                minIndex = i;
            }
        }
        d[l][j] = min;
        if (str.charAt(l) == '(' && str.charAt(j) == ')' || str.charAt(l) == '[' && str.charAt(j) == ']') {
            int x = minLen(l + 1, j - 1, str) + 2;
            if (x < d[l][j]) {
                d[l][j] = x;
                minIndex = -10;
            }
        }
        return minIndex;
    }

    private static int minLen(int l, int j, String str) {
        if (l == j) {
            d[l][j] = 2;
            return 2;
        }
        if (l > j) {
            d[l][j] = 0;
            return 0;
        }
        if (d[l][j] == -1) {
            if (str.charAt(l) == ')' || str.charAt(l) == ']') {
                d[l][j] = 2 + minLen(l + 1, j, str);
            } else if (str.charAt(j) == '(' || str.charAt(j) == '[') {
                d[l][j] = 2 + minLen(l, j - 1, str);
            } else {
                int min = 2 + minLen(l + 1, j, str);
                int sum = 2 + minLen(l, j - 1, str);
                if (min > sum) { min = sum; }
                for (int i = l + 1; i < j; i++) {
                    sum = minLen(l, i, str) + minLen(i + 1, j, str);
                    if (min > sum) { min = sum; }
                }
                d[l][j] = min;
                if (str.charAt(l) == '(' && str.charAt(j) == ')' || str.charAt(l) == '[' && str.charAt(j) == ']') {
                    int x = minLen(l + 1, j - 1, str) + 2;
                    if (x < d[l][j]) { d[l][j] = x; }
                }
            }
        }
        return d[l][j];
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
