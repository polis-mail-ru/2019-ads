package ru.mail.polis.ads.part1.makaryb;

// 1087

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    // рассматриваемая последовательность
    private static String pts = "";
    // repair - информация для восстановления строки,
    // считаемой за правильную;
    // minS - наименьшее количество символов,
    // которые необходимо вставить в строку,
    // чтобы она стала правильной.
    private static List<List<Integer>> repair = new ArrayList<>();
    private static List<List<Integer>> minS = new ArrayList<>();

    private static final int defValue = -1;
    private static final int defInf = MAX_VALUE;

    private SecondTask() {}

    private static void solve(final Scanner in) {
        pts = in.nextLine();
        int len = pts.length();

        // Исходная последовательность содержит
        // не более 100 скобок (по условию)
        int max = 100;
        if (len > max) {
            System.out.println("Вы ввели больше " + max + " скобок");
            System.exit(1);
        }

        for (int col = 0; col < len; col++) {
            List<Integer> row1 = new ArrayList<>();
            List<Integer> row2 = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                row1.add(defValue);
                row2.add(defInf);
            }
            repair.add(row1);
            minS.add(row2);
        }

        //выведет дефолтную матрицу
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++)
//                System.out.print(repair.get(i).get(j));
//            System.out.print("\n");
//        }

        fillSolutionMatrices(0, len-1);

        // выведет текущую переходную матрицу
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++)
//                System.out.print(repair.get(i).get(j)+" ");
//            System.out.print("\n");
//        }
//        System.out.print("\n");

        printResult(0, len-1);

        System.out.print("\n");
    }

    private static int fillSolutionMatrices(int i, int j) {
        if (i == j) return 1;
        // неправильный вход
        else if (i > j) return 0;
        // когда записали
        if (minS.get(i).get(j) != MAX_VALUE)
            return minS.get(i).get(j);
        // правильная ли постановка символов
        if ((pts.charAt(i) == '(' && pts.charAt(j) == ')') || (pts.charAt(i) == '[' && pts.charAt(j) == ']'))
            minS.get(i).set(j, min(minS.get(i).get(j), fillSolutionMatrices(i+1,j-1)));
        // составляем матрицы minS и repair для нашего ввода
        for (int k = i; k < j; k++) {
            int temp = fillSolutionMatrices(i,k) + fillSolutionMatrices(k+1,j);
            if (temp < minS.get(i).get(j)) {
                repair.get(i).set(j,k);
                minS.get(i).set(j, temp);
            }
        }
        return minS.get(i).get(j);
    }

    private static void printResult(int i, int j) {
        if (i > j) return;
        if (i == j) {
            if (pts.charAt(i) == '(' || pts.charAt(i) == ')')
                System.out.print("()");
            else
                System.out.print("[]");
        } else if (repair.get(i).get(j) == -1) {
            System.out.print(pts.charAt(i));
            printResult(i + 1, j - 1);
            System.out.print(pts.charAt(j));
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