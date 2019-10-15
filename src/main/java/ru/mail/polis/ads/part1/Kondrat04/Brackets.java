package ru.mail.polis.ads.part1.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Brackets {

    private static String startSequence;
    private static String finalSequence;
    private static int[][] completeTable;
    private static int support;

    private static void solve(final Scanner in, final PrintWriter out) {
        startSequence = in.nextLine();
        if (startSequence.equals("")){
            out.write("");
            return;
        }
        finalSequence = "";
        int n = startSequence.length();
        completeTable = new int[n][n];
        int[][] minValue = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    minValue[i][j] = 0;
                    completeTable[i][j] = 0;
                }
                else if (i == j) {
                    minValue[i][j] = 1;
                    completeTable[i][j] = i;
                }
            }
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                int j = i + k;
                int min = Integer.MAX_VALUE;
                int ideal = i + 1;
                for (int h = i + 1; h <= j; h++) {
                    min = Integer.min(min, minValue[i][h - 1] + minValue[h][j]);
                    if (min == minValue[i][h-1]+minValue[h][j])
                        ideal = h;
                }
                if (isPerfect(i, j)) {
                    min = Integer.min(min, minValue[i + 1][j - 1]);
                    if (min == minValue[i + 1][j - 1]){
                        ideal = -1;
                    }
                }
                minValue[i][j] = min;
                completeTable[i][j] = ideal;
            }
        }
        support = 0;
        complete(0, n-1);
        finalSequence += startSequence.substring(support, n);
        out.write(finalSequence);
    }

    private static void complete(int left, int right){
        if (left == right){
            finalSequence += startSequence.substring(support, left);
            switch (startSequence.charAt(left)){
                case '(':
                case ')':
                    finalSequence += "()";
                    break;
                case '[':
                case ']':
                    finalSequence += "[]";
                    break;
            }
            support = left + 1;
        }
        else if (completeTable[left][right] == -1)
            complete(left + 1, right - 1);
        else if (completeTable[left][right] != 0){
            complete(left, completeTable[left][right] - 1);
            complete(completeTable[left][right], right);
        }
    }

    private static boolean isPerfect(int left, int right) {
        return (startSequence.charAt(left) == '(' && startSequence.charAt(right) == ')' ||
                startSequence.charAt(left) == '[' && startSequence.charAt(right) == ']');
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
