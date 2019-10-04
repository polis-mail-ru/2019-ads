package ru.mail.polis.ads.part2.medalexey;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *  Задача: Скобочные последовательности (https://www.e-olymp.com/ru/problems/5327)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5777524
 */
public class BracketSequences {

    private BracketSequences() {

    }


    private static void solve(final Scanner in, final PrintWriter out) {
        final String sequence = in.nextLine();
        out.println( (isSequenceCorrect(sequence)) ? "YES" : "NO");
    }


    private static boolean isSequenceCorrect(final String sequence) {
        final Deque<Character> stack = new ArrayDeque<>();

        for (char item: sequence.toCharArray()) {
            if (item == '(') {
                stack.push(item);
            } else {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
