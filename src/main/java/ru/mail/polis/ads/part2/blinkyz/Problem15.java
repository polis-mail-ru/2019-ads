package ru.mail.polis.ads.part2.blinkyz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem15 {
    private Problem15() {
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        final int[][] c = new int[n][m]; // cost of each point

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = in.nextInt();
            }
        }

        // d[i][j] is max sum on path to here
        final int[][] d = new int[n][m];
        // from[i][j] is either 'R' or 'F', that is the way we've come to this point
        final char[][] from = new char[n][m];

        // start point indexes
        final int startI = n - 1;
        final int startJ = 0;
        // end point indexes
        final int endI = 0;
        final int endJ = m - 1;

        /* DYNAMIC BASE CASES FILLING START*/

        // set start point max sum equal to start point cost
        d[startI][startJ] = c[startI][startJ];

        int baseI = n - 2;
        int baseJ = 1;
        // fill the very first column, such as j = 0 and 0 <= i <= n - 2
        while (baseI >= 0) {
            d[baseI][0] = d[baseI + 1][0] + c[baseI][0];
            from[baseI][0] = 'F';
            baseI--;
        }

        // fill the very first row, such as i = n-1 and 1 <= j <= m - 1
        while (baseJ < m) {
            d[n - 1][baseJ] = d[n - 1][baseJ - 1] + c[n - 1][baseJ];
            from[n - 1][baseJ] = 'R';
            baseJ++;
        }

        /* DYNAMIC BASE CASES FILLING END*/

        for (int j = 1; j < m; j++) {
            for (int i = n - 2; i >= 0; i--) {
                final int toUp = d[i + 1][j] + c[i][j];
                final int toRight = d[i][j - 1] + c[i][j];

                if (toUp >= toRight) {
                    d[i][j] = toUp;
                    from[i][j] = 'F';
                } else {
                    d[i][j] = toRight;
                    from[i][j] = 'R';
                }
            }
        }

        restore(startI, startJ, endI, endJ, from);
    }

    private static void restore(final int startI, final int startJ, final int endI, final int endJ, final char[][] from) {
        List<Character> path = new ArrayList<>();
        int i = endI;
        int j = endJ;
        char curFrom;
        while (true) {
            if (i == startI && j == startJ) {
                break;
            }
            curFrom = from[i][j];
            path.add(curFrom);
            if (curFrom == 'R') {
                j = j - 1;
            } else {
                i = i + 1;
            }
        }
        for (int index = path.size() - 1; index >= 0; index--) {
            System.out.print(path.get(index));
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
