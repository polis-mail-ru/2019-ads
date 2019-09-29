package ru.mail.polis.ads.part1.makaryb;

// 1090

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Made by БорискинМА
 * 29.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5742192
 */
public final class FourthTask {

    private static Logger logger = Logger.getLogger(FourthTask.class.getName());

    private FourthTask() {}

    private static void solve(final Scanner in) {
        final String x = in.nextLine();
        final int len = x.length();

        final String result = String.valueOf(recalculateAndPack(x,len));

        logger.log(Level.INFO, result);
    }

    private static String recalculateAndPack(final String startIn, final int length) {
        String[][] finalOut;
        finalOut = new String[length][length];

        for (int i = 1; i < length + 1; i++) {
            for (int l = 0; i + l - 1 < length; l++) {

                final int r = i + l - 1;
                String compressed = startIn.substring(l, r + 1);

                if (i > 4) {
                    compressed = pack(l, r, finalOut, compressed);

                    for (int cursor = 1; cursor < i; cursor++) {
                        if (i % cursor == 0 && hasPeriod(startIn, l, cursor, r)) {
                            final String temp = i / cursor + "(" + finalOut[l][l + cursor -1] + ")";
                            compressed = periodicEvaluation(compressed, temp);
                        }
                    }
                }
                finalOut[l][r] = compressed;
            }
        }
        return finalOut[0][length - 1];
    }

    private static String periodicEvaluation(String compressed, String temporaryCompressed) {
        if (temporaryCompressed.length() < compressed.length()) {
            compressed = temporaryCompressed;
        }
        return compressed;
    }

    private static boolean hasPeriod(final String startIn, final int lBorder, final int cursor, final int rBorder) {
        for (int j = lBorder + cursor; j < rBorder + 1; j++) {
            if (startIn.charAt(j) != startIn.charAt(j - cursor)) {
                return false;
            }
        }
        return true;
    }

    private static String pack(final int l, final int r, final String[][] finalOut, final String compressed) {
        String temporary = compressed;
        for (int inRBorder = l; inRBorder < r; inRBorder++) {
            final int inLBorder = inRBorder + 1;
            final String symbol = finalOut[l][inRBorder] + finalOut[inLBorder][r];
            if (symbol.length() < temporary.length()) {
                temporary = symbol;
            }
        }
        return temporary;
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        solve(in);
    }
}
