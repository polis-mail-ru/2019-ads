package main.java.ru.mail.polis.ads.part2.Eretic431;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858792
 */

public class Task1 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int m = in.nextInt() + 1;
        int n = in.nextInt() + 1;
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m - 1; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i][j] + Math.max(matrix[i + 1][j], matrix[i][j - 1]);
            }
        }

        final Deque<Character> deq = new ArrayDeque<>();
        int i = 0;
        int j = n - 1;
        while (i < m - 2 || j > 1) {
            if (j > 1 && matrix[i][j - 1] >= matrix[i + 1][j]) {
                deq.push('R');
                j--;
            } else {
                deq.push('F');
                i++;
            }
        }

        deq.forEach(System.out::print);
    }
}
