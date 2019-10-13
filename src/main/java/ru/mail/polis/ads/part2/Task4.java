package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task4 {
    private static Deque<Character> stack = new ArrayDeque<>();

    private static final char ROUND_BRACKET_OPENED = '(';
    private static final char ROUND_BRACKET_CLOSED = ')';

    private static final char SQUARE_BRACKET_OPENED = '[';
    private static final char SQUARE_BRACKET_CLOSED = ']';

    private static final char FIGURE_BRACKET_OPENED = '{';
    private static final char FIGURE_BRACKET_CLOSED = '}';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        if (checkSequence(s)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean checkSequence(String sequence) {
        if (sequence == null) {
            return false;
        }

        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case (FIGURE_BRACKET_OPENED):
                case (SQUARE_BRACKET_OPENED):
                case (ROUND_BRACKET_OPENED):
                    stack.add(sequence.charAt(i));
                    break;

                case (FIGURE_BRACKET_CLOSED):
                    if (isNotContains(FIGURE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                case (SQUARE_BRACKET_CLOSED):
                    if (isNotContains(SQUARE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                case (ROUND_BRACKET_CLOSED):
                    if (isNotContains(ROUND_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                default:
                    return false;
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isNotContains(char a) {
        try {
            if (a == stack.getLast()) {
                stack.removeLast();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
    }
}
