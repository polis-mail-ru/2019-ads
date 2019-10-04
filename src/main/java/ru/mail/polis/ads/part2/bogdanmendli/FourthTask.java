package ru.mail.polis.ads.part2.bogdanmendli;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class FourthTask {

    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char CLOSE_ROUND_BRACKET = ')';
    private static Deque<Character> stack;
    private static String sequence;

    private static void solve() {
        Scanner in = new Scanner(System.in);
        stack = new ArrayDeque<>();
        sequence = in.next();
        if (isCorrectFirstElement()) {
            System.out.println("NO");
            return;
        }
        checkSequence();
    }

    private static boolean isCorrectFirstElement() {
        return sequence.charAt(0) == CLOSE_ROUND_BRACKET;
    }

    private static void checkSequence() {
        stack.push(sequence.charAt(0));
        for (int i = 1; i < sequence.length(); i++) {
            char temp = sequence.charAt(i);
            if (temp == CLOSE_ROUND_BRACKET) {
                if (stack.isEmpty() || stack.pop() != OPEN_ROUND_BRACKET) {
                    System.out.println("NO");
                    return;
                }
            } else {
                stack.push(temp);
            }
        }
        if (stack.isEmpty()) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    public static void main(String[] args) {
        solve();
    }
}
