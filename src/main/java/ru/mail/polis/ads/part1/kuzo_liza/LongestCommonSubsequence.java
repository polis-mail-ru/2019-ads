package ru.mail.polis.ads.part1.kuzo_liza;

import java.util.Scanner;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int second[] = new int[m];
        for (int i = 0; i < m; i++) {
            second[i] = scanner.nextInt();
        }

        if ((n == 0) || (m == 0)){
            System.out.println(0);
        }

        int array[][] = new int[n+1][m+1];
        int max = 0;

        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++) {
                if (first[i-1] == second[j-1]) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {
                    array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
                }

                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        System.out.print(max);
    }
}