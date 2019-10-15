package ru.mail.polis.ads.part1.Kungurov;

import java.util.Scanner;

/**
 * created by kirill.kungurov on 26.09.2019
 * https://www.e-olymp.com/ru/submissions/5715074
 */
public final class SolveOfProblem1 {

    private static void solve(int in) {
        System.out.println(in / 10 + " " + in % 10);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        solve(in.nextInt());
    }
}
