package ru.mail.polis.ads.part1.makaryb;

// 1090

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Made by БорискинМА
 * 29.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5742192
 */
public final class FourthTask {

    private FourthTask() {}

    private static void solve(final Scanner in, final PrintWriter out) {
        String x = in.nextLine();
        int len = x.length();

        System.out.println(recalculateAndPack(x, len));

        out.flush();
    }

    private static String recalculateAndPack(String startIn, int length) {
        String[][] finalOut;
        finalOut = new String[length][length];

        for (int i = 1; i < length + 1; i++) {
            for (int lBorder = 0; i + lBorder - 1 < length; lBorder++) {

                int rBorder = i + lBorder - 1;
                String compressed = startIn.substring(lBorder, rBorder + 1);

                if (i > 4) {
                    compressed = defaultPack(lBorder, rBorder, finalOut, compressed);
                    compressed = packPeriodic(i, startIn, lBorder, rBorder, compressed, finalOut);
                }
                finalOut[lBorder][rBorder] = compressed;
            }
        }
        return finalOut[0][length - 1];
    }

    private static boolean hasPeriod(String startIn, int lBorder, int cursor, int rBorder) {
        for (int j = lBorder + cursor; j < rBorder + 1; j++) {
            if (startIn.charAt(j) != startIn.charAt(j - cursor))
                return false;
        }
        return true;
    }

    private static String packPeriodic(int length, String startIn, int lBorder, int rBorder, String compressed, String[][] finalOut) {
        for (int cursor = 1; cursor < length; cursor++) {
            if (length % cursor == 0) {
                if (hasPeriod(startIn, lBorder, cursor, rBorder)) {
                    String temp = length / cursor + "(" + finalOut[lBorder][lBorder + cursor -1] + ")";
                    if (temp.length() < compressed.length())
                        compressed = temp;
                }
            }
        }
        return compressed;
    }

    private static String defaultPack(int lBorder, int rBorder, String[][] finalOut, String compressed) {
        for (int inRBorder = lBorder; inRBorder < rBorder; inRBorder++) {
            int inLBorder = inRBorder + 1;
            String symbol = finalOut[lBorder][inRBorder] + finalOut[inLBorder][rBorder];
            if (symbol.length() < compressed.length())
                compressed = symbol;
        }
        return compressed;
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
