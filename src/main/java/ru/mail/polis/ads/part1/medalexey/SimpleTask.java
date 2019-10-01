package ru.mail.polis.ads.part1.medalexey;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *   Название задачи: "Простая задача?"
 *   Тестирование: https://www.e-olymp.com/ru/submissions/5723843
 */
public final class SimpleTask {

    private SimpleTask() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int inputData = in.nextInt();
        out.write(inputData/10 + " " + inputData%10);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        solve(in, out);
        out.close();
        in.close();
    }

}
