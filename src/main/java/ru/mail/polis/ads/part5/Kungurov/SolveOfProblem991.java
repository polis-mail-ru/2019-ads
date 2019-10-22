package ru.mail.polis.ads.part5.Kungurov;


import java.io.PrintWriter;
import java.util.Scanner;

/**
 * created by kirill kungurov on 22.10.2019
 * https://www.e-olymp.com/ru/submissions/5926104
 */

public class SolveOfProblem991 {

    private static void solve(final Scanner in, final PrintWriter out) {
        String word = in.nextLine();
        String word2 = in.nextLine();

        boolean[][] d = new boolean[word.length() + 1][word2.length() + 1];
        d[0][0] = true;

        for (int i = 1; i < word.length() + 1; i++) {
            char c1 = word.charAt(i - 1);
            for (int j = 1; j < word2.length() + 1; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2 || c1 == '?' || c2 == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (c1 == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i][j - 1] || d[i - 1][j];
                } else if (c2 == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1];

                }
            }
        }
        out.println(d[d.length - 1][d[0].length - 1] ? "YES" : "NO");
    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
