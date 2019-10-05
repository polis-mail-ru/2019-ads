package ru.mail.polis.ads.part2.medalexey;

import java.io.PrintWriter;
import java.util.Scanner;


/**
 *  Задача: Мышка и зернышки (https://www.e-olymp.com/ru/problems/15)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5776840
 */
public class MouseAndSeeds {

    private MouseAndSeeds() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final String floorSize = in.nextLine();
        final int floorWidth = Integer.parseInt(floorSize.split(" ")[0]);
        final int floorLength = Integer.parseInt(floorSize.split(" ")[1]);

        final int[][] matrix = new int[floorWidth+1][floorLength+1];

        fillMatrix(matrix, in);
        countBestWayForEachCell(matrix);
        out.println(findBestWay(matrix));
    }


    // fill the matrix with input values
    private static void fillMatrix(final int[][] matrix, final Scanner in) {
        for (int i = 0; i < matrix.length-1; ++i) {
            final String[] line = in.nextLine().split(" ");
            for (int j = 1; j < matrix[0].length; ++j) {
                matrix[i][j] = Integer.parseInt(line[j-1]);
            }
        }
    }


    private static void countBestWayForEachCell(final int[][] matrix) {
        for (int i = matrix.length - 2; i >= 0; --i) {
            for (int j = 1; j < matrix[0].length; ++j) {
                matrix[i][j] = Math.max(matrix[i+1][j] + matrix[i][j], matrix[i][j-1] + matrix[i][j]);
            }
        }
    }


    private static String findBestWay(final int[][] matrix) {
        final StringBuilder result = new StringBuilder();
        int i = 0;
        int j = matrix[0].length - 1;

        while (i != matrix.length-2 || j != 1) {
            if (matrix[i][j-1] > matrix[i+1][j] || i + 1 == matrix.length - 1) {
                result.insert(0,"R");
                j--;
            } else {
                result.insert(0, "F");
                i++;
            }
        }

        return result.toString();
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
