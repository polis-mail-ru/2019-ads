package ru.mail.polis.ads.part1.gogun;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args)

    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int x[] = new int[n+1];
        for(int i = 1; i <= n; i++) x[i] = input.nextInt();

        int m = input.nextInt();
        int y[] = new int[m+1];
        for(int i = 1; i <= m; i++) y[i] = input.nextInt();

        int[][] resultArr = new int[2][m+1];

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (x[i] == y[j]) {
                    resultArr[i % 2][j] = 1 + resultArr[(i - 1) % 2][j - 1];
                }
                else {
                    resultArr[i % 2][j] = Math.max(resultArr[(i - 1) % 2][j], resultArr[i % 2][j - 1]);
                }
            }
        }

        System.out.println(resultArr[n%2][m]);
    }

}
