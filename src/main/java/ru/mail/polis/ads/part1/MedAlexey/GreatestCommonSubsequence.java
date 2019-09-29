package ru.mail.polis.ads.part1.MedAlexey;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *  Название задачи: Наибольшая общая подпоследовательность
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5741410
 */
public class GreatestCommonSubsequence {

    private GreatestCommonSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        int firstLineLength = Integer.parseInt(in.nextLine());
        String[] firstLine = in.nextLine().split(" ");
        int secondLineLength = Integer.parseInt(in.nextLine());
        String[] secondLine = in.nextLine().split(" ");
        int[][] matrix = new int[firstLineLength+1][secondLineLength+1];

        fillMatrix(matrix, firstLine, secondLine);

        out.println(matrix[firstLineLength][secondLineLength]);
    }


    private static void fillMatrix(int[][] matrix, String[] firstLine, String[] secondLine) {

        for (int i = 1; i <= firstLine.length; i++) {
            for (int j = 1; j <= secondLine.length; j++) {
                if (Integer.parseInt(firstLine[i-1]) == Integer.parseInt(secondLine[j-1])) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
