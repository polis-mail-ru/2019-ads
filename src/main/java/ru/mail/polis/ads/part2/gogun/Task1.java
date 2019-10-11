package ru.mail.polis.ads.part2.gogun;

import java.io.*;
import java.util.*;

public class Task1 {
    static int[][] field;
    static char[] pos;

    private static void makeArray(int m, int n, Scanner input) {
        field = new int[m+2][n+2];
        pos = new char[n*m];

        for (int[] row: field)
            Arrays.fill(row, -1);

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                field[m-i+1][j] = input.nextInt();
            }
        }

        field[0][1] = 0;
    }

    private static void makingOutputArray(int m, int n, PrintWriter output) {
        int index = 0;
        while (m + n > 2) {
            if (field[m-1][n] > field[m][n-1]) {
                pos[index] = 'F';
                m--;
            } else {
                pos[index] = 'R';
                n--;
            }
            index++;
        }

        while(index-- != 0) {
            output.print(pos[index]);
        }
    }

    private static void solve(Scanner input, PrintWriter output) {
        int m = input.nextInt();
        int n = input.nextInt();

        makeArray(m, n, input);

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                field[i][j] = Math.max(field[i-1][j], field[i][j-1]) + field[i][j];
            }
        }

        makingOutputArray(m, n, output);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
