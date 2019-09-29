package ru.mail.polis.ads.part1.MedAlexey;

import java.io.PrintWriter;
import java.util.*;

/**
 * Название задачи: упаковка символов
 * Тестирование: https://www.e-olymp.com/ru/submissions/5738646
 */
public class PackagingSymbols {

    private PackagingSymbols() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        String input = in.nextLine();
        String[][] matrix = new String[input.length()][input.length()];

        // перебираем все возможные длины подстрок входной строки
        for (int len = 1; len <= input.length(); len++) {
            // проходим по всем таким подстрокам
            for (int left = 0; left + len - 1 < input.length(); left++) {
                //определяем конец текущей подстроки
                int right = left + len -1;
                // определяем текущую подстроку
                String min_substring = input.substring(left, right+1);

                // если длина подстроки меньше 4, то её нет смысла упаковывать
                if(len > 4) {
                    // поиск минимальной подстроки данной подстроки
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String tmp = matrix[left][right1] + matrix[left2][right];

                        if (tmp.length() < min_substring.length()) {
                            min_substring = tmp;
                        }
                    }

                    // определяем периодичность строки
                    for (int period = 1; period < len; period++) {
                        if (len%period == 0) {
                            boolean isPeriodic = true;
                            for (int i = left + period; i <= right; i++) {
                                if (input.charAt(i) != input.charAt(i - period)) {
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            // упаковываем строку, если она периодична
                            if (isPeriodic) {
                                String tmp = len/period + "(" + matrix[left][left + period - 1] + ")";
                                if (tmp.length() < min_substring.length()) {
                                    min_substring = tmp;
                                }
                            }
                        }
                    }
                }

                matrix[left][right] = min_substring;
            }
        }

        out.println(matrix[0][input.length()-1]);
    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
