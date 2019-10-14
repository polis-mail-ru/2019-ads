package ru.mail.polis.ads.part1.DiscreetDmitriy;

import java.util.Scanner;

public class Task2BracketsSequence {
    private Task2BracketsSequence() {}

    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();

        int length = str.length();

        if (length == 0) {
            System.out.println();
            return;
        }

        int[][] matrix = new int[length][length];
        int[][] split = new int[length][length];

        for (int j = 0; j < length; j++)
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    matrix[i][j] = 1;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                int splitMin = -1;

                if (str.charAt(i) == '(' && str.charAt(j) == ')' ||
                        str.charAt(i) == '[' && str.charAt(j) == ']')
                    min = matrix[i + 1][j - 1];

                for (int k = i; k < j; k++)
                    if (matrix[i][k] + matrix[k + 1][j] < min) {
                        min = matrix[i][k] + matrix[k + 1][j];
                        splitMin = k;
                    }
                matrix[i][j] = min;
                split[i][j] = splitMin;
            }
        restore(0, length - 1, str, matrix, split);
    }

    private static void restore(int i, int j, String s, int[][] d, int[][] split) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
            }
            return;
        }
        if (d[i][j] == 0) {
            System.out.print(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s, d, split);
            System.out.print(s.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, s, d, split);
        restore(k + 1, j, s, d, split);
    }
}

//  https://www.e-olymp.com/ru/submissions/5853001

