package ru.mail.polis.ads.part1.makaryb;

// 1

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Modified by БорискинМА
 * 27.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5712136
 */
public final class FirstTask {

    private static Logger logger = Logger.getLogger(FirstTask.class.getName());

    private FirstTask() {}

    private static void solve(final Scanner in) {
        final int total = in.nextInt();
        int digit1;
        int digit2;

        digit1 = total / 10;
        digit2 = total % 10;

        final String result = digit1 + " " + digit2;

        logger.log(Level.INFO, result);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        solve(in);
    }
}
