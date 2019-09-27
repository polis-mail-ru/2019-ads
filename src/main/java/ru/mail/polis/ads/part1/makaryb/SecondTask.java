package ru.mail.polis.ads.part1.makaryb;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/**
 * Made by БорискинМА
 * 28.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 81%: https://www.e-olymp.com/ru/submissions/5731171
 */
public final class SecondTask {
    // Исходная последовательность содержит
    // не более 100 скобок (по условию)
    private static int max = 100;
    // рассматриваемая последовательность
    private static String pts = "";
    // информация для восстановления строки,
    // считаемой за правильную
    private static int[][] repair = new int[max][max];
    // minS - наименьшее количество символов,
    // которые необходимо вставить в строку,
    // чтобы она стала правильной
    private static int[][] minS = new int[max][max];

    private SecondTask() {}

    private static void solve(final Scanner in, final PrintWriter out) {
        pts = in.nextLine();
        int len = pts.length();

        for (int[] ints : repair)
            Arrays.fill(ints, -1);

        for (int[] ints : minS)
            Arrays.fill(ints, 0x3F);

        fillSolutionMatrices(0, len-1);
        printResult(0, len-1);

        out.flush();
    }

    private static int fillSolutionMatrices(int i, int j) {
        // пустая строка
        if (i == j) return 1;
        // неправильный вход
        else if (i > j) return 0;
        // когда записали
        if (minS[i][j] != 0x3F)
            return minS[i][j];
        // правильная ли постановка символов
        if ((pts.charAt(i) == '(' && pts.charAt(j) == ')') || (pts.charAt(i) == '[' && pts.charAt(j) == ']'))
            minS[i][j] = min(minS[i][j], fillSolutionMatrices(i+1,j-1));
        // составляем матрицы minS и repair для нашего ввода
        for (int k = i; k < j; k++) {
            int temp = fillSolutionMatrices(i,k) + fillSolutionMatrices(k+1,j);
            if (temp < minS[i][j]) {
                repair[i][j] = k;
                minS[i][j] = temp;
            }
        }
        return minS[i][j];
    }

    private static void printResult(int i, int j) {
        if (i>j) return;
        if (i == j) {
            if (pts.charAt(i) == '(' || pts.charAt(i) == ')')
                System.out.print("()");
            else
                System.out.print("[]");
        } else if (repair[i][j] == -1) {
            System.out.print(pts.charAt(i));
            printResult(i + 1, j - 1);
            System.out.print(pts.charAt(j));
        } else {
            printResult(i, repair[i][j]);
            printResult(repair[i][j] + 1, j);
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}