package ru.mail.polis.ads.part5.medalexey;

import java.io.PrintWriter;
import java.util.Scanner;


/**
 *  Задача: Шаблон и слово https://www.e-olymp.com/ru/problems/991
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5946762
 */
public class Template {

    private static String line;
    private static String template;
    private static boolean[][] matrix;

    private Template() {
    }


    private static void solve(final Scanner in, final PrintWriter out) {
        readTemplateAndLine(in);

        initMatrix();

        fillMatrix();

        out.println(
                (matrix[line.length()][template.length()]) ? "YES" : "NO"
        );
    }


    private static void initMatrix() {
        matrix = new boolean[line.length() + 1][template.length() + 1];
        matrix[0][0] = true;
    }


    private static void fillMatrix() {

        for (int i = 1; i <= line.length(); i++) {
            for (int j = 1; j <= template.length(); j++) {
                if (line.charAt(i - 1) == template.charAt(j - 1) || template.charAt(j - 1) == '?') {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else if (template.charAt(j - 1) == '*') {
                    matrix[i][j] = matrix[i - 1][j] || matrix[i - 1][j - 1] || matrix[i][j - 1];
                } else {
                    matrix[i][j] = false;
                }
            }
        }

    }


    private static void readTemplateAndLine(final Scanner in) {
        line = in.nextLine();

        if (line.contains("*") || line.contains("?")) {
            template = line;
            line = in.nextLine();
        } else {
            template = in.nextLine();
        }
    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
