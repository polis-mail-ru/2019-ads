package ru.mail.polis.ads.part5.medalexey;

import java.io.PrintWriter;
import java.util.Scanner;


/**
 *  Задача: Дипломы  https://www.e-olymp.com/ru/problems/3969
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5937614
 */
public class Diploma {

    private static long diplomaWidth;
    private static long diplomaHeight;
    private static long numberOfDiplomas;

    private Diploma() {
    }


    private static void solve(Scanner in, PrintWriter out) {
        diplomaWidth = in.nextInt();
        diplomaHeight = in.nextInt();
        numberOfDiplomas = in.nextInt();

        out.println(
                findResult(
                        Math.max(diplomaWidth, diplomaHeight),
                        numberOfDiplomas * Math.max(diplomaWidth, diplomaHeight)
                )
        );
    }


    private static long findResult(long left, long right) {
        long median;
        long curNumberOfDiplomas;

        while (left < right) {
            median = (left + right) / 2;
            curNumberOfDiplomas = (median / diplomaWidth) * (median / diplomaHeight);

            if (curNumberOfDiplomas >= numberOfDiplomas) {
                right = median;
            } else {
                left = median + 1;
            }
        }

        return right;
    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
