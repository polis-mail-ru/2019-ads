package ru.mail.polis.ads.part1.makaryb;

// 1087

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

/**
 * Made by БорискинМА
 * 28.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5743083
 */
public final class SecondTask {

    private static Logger logger = Logger.getLogger(SecondTask.class.getName());
    // рассматриваемая последовательность
    private static String pts = "";
    // repair - информация для восстановления строки,
    // считаемой за правильную.
    // minS - наименьшее количество символов,
    // которые необходимо вставить в строку,
    // чтобы она стала правильной.
    private static List<List<Integer>> repair = new ArrayList<>();
    private static List<List<Integer>> minS = new ArrayList<>();

    private SecondTask() {}

    private static void solve(final Scanner in) {
        pts = in.nextLine();
        final int len = pts.length();

        // Исходная последовательность содержит
        // не более 100 скобок (по условию)
        final int max = 100;
        if (len > max) {
            logger.log(Level.INFO,"Вы ввели больше " + max + " скобок");
            System.exit(1);
        }

        for (int col = 0; col < len; col++) {
            final List<Integer> row1 = new ArrayList<>();
            final List<Integer> row2 = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                row1.add(-1);
                row2.add(MAX_VALUE);
            }
            repair.add(row1);
            minS.add(row2);
        }

        fillSolutionMatrices(0, len-1);

        printResult(0, len-1);
    }

    private static int fillSolutionMatrices(final int i, final int j) {
        if (i == j) {
            return 1;
        }
        // неправильный вход
        else if (i > j) {
            return 0;
        }
        // когда записали
        if (minS.get(i).get(j) != MAX_VALUE) {
            return minS.get(i).get(j);
        }
        // правильная ли постановка символов
        if (pts.charAt(i) == '(' && pts.charAt(j) == ')' || pts.charAt(i) == '[' && pts.charAt(j) == ']') {
            minS.get(i).set(j, min(minS.get(i).get(j), fillSolutionMatrices(i + 1, j - 1)));
        }
        // составляем матрицы minS и repair для нашего ввода
        for (int k = i; k < j; k++) {
            final int temp = fillSolutionMatrices(i,k) + fillSolutionMatrices(k+1,j);
            if (temp < minS.get(i).get(j)) {
                repair.get(i).set(j,k);
                minS.get(i).set(j, temp);
            }
        }
        return minS.get(i).get(j);
    }

    private static void printResult(final int i, final int j) {
        if (i > j) {
            return;
        }
        if (i == j) {
            if (pts.charAt(i) == '(' || pts.charAt(i) == ')') {
                logger.log(Level.INFO,"()");
            }
            else {
                logger.log(Level.INFO,"[]");
            }
        } else if (repair.get(i).get(j) == -1) {
            logger.log(Level.INFO, String.valueOf(pts.charAt(i)));
            printResult(i + 1, j - 1);
            logger.log(Level.INFO, String.valueOf(pts.charAt(j)));
        } else {
            printResult(i, repair.get(i).get(j));
            printResult(repair.get(i).get(j) + 1, j);
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        solve(in);
    }
}