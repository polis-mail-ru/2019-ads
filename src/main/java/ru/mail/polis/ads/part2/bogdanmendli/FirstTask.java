package ru.mail.polis.ads.part2.bogdanmendli;

import java.util.Scanner;

public class FirstTask {

    private static int[][] paths;

    private static void solve(final Scanner in) {
        final int m = in.nextInt();
        final int n = in.nextInt();
        paths = new int[m][n];
        int[][] grains = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grains[i][j] = in.nextInt();
            }
        }
        paths[m - 1][0] = grains[m - 1][0];
        for (int i = 1; i < n; i++) {
            paths[m - 1][i] = grains[m - 1][i] + paths[m - 1][i - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            paths[i][0] = grains[i][0] + paths[i + 1][0];
        }

        for (int i = m - 2; i >=0 ; i--) {
            for (int j = 1; j < n; j++) {
                if (paths[i][j - 1] > paths[i + 1][j]) {
                    paths[i][j] = grains[i][j] + paths[i][j - 1];
                    continue;
                }
                paths[i][j] = grains[i][j] + paths[i + 1][j];
            }
        }

        System.out.println(restorePath(m, n));
    }

    private static String restorePath(final int m, final int n) {
        String result = "";
        int right = n - 1;
        int front = 0;
        while (right != 0 && front != m - 1) {
            if (paths[front][right - 1] > paths[front + 1][right]) {
                result += "R";
                right--;
                continue;
            }
            result += "F";
            front++;
        }
        if (front == m - 1) {
            while (right != 0) {
                right--;
                result += "R";
            }
        } else {
            while (front != m - 1) {
                front++;
                result += "F";
            }
        }

        return reversePath(result);
    }

    private static String reversePath(String path) {
        String temp = "";
        for (int i = 0; i < path.length(); i++) {
            temp += path.charAt(path.length() - i - 1);
        }
        return temp;
    }

    public static void main(String[] args) {
        solve(new Scanner(System.in));
    }
}
