package ru.mail.polis.ads.part1.nekobitlz;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Task2 {

    private static int[][] brackets;
    private static int[][] positions;
    private static String str;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        str = in.nextLine();
        int length = str.length();

        brackets = new int[length][length];
        positions = new int[length][length];

        if (!isValid(str)) {
            out.println(str);
            out.close();
            return;
        }

        initialize2DArray(brackets);
        findBracketsPositions(0, length - 1);

        out.println(buildString(0, length - 1));
        out.close();
    }

    private static boolean isValid(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')' ||
                    str.charAt(i) == ']' || str.charAt(i) == '[') {
                return true;
            }
        }

        return false;
    }

    private static int findBracketsPositions(int begin, int end) {
        if (begin > end) return 0;

        if (brackets[begin][end] != -1) return brackets[begin][end];

        brackets[begin][end] = findBracketsPositions(begin + 1, end) + 2;
        positions[begin][end] = begin;

        char closingChar;
        switch (str.charAt(begin)) {
            case '(':
                closingChar = ')';
                break;
            case '[':
                closingChar = ']';
                break;
            default:
                return brackets[begin][end];
        }

        for (int i = begin + 1; i <= end; i++) {
            if (str.charAt(i) == closingChar) {
                int current = findBracketsPositions(begin + 1, i - 1) + findBracketsPositions(i + 1, end) + 2;

                if (current < brackets[begin][end]) {
                    brackets[begin][end] = current;
                    positions[begin][end] = i;
                }
            }
        }

        return brackets[begin][end];
    }

    private static String buildString(int begin, int end) {
        if (begin > end) return "";

        if (positions[begin][end] == begin) {
            if (str.charAt(begin) == '(' || str.charAt(begin) == ')')
                return "()" + buildString(begin + 1, end);

            if (str.charAt(begin) == '[' || str.charAt(begin) == ']')
                return "[]" + buildString(begin + 1, end);
        }

        return str.charAt(begin)
                + buildString(begin + 1, positions[begin][end] - 1)
                + str.charAt(positions[begin][end])
                + buildString(positions[begin][end] + 1, end);
    }

    private static void initialize2DArray(int[][] array) {
        for (int[] row : array) {
            Arrays.fill(row, -1);
        }
    }
}
