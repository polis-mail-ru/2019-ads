package ru.mail.polis.ads.part2.stasmilke;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Task5327 {
    private Task5327() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        String sequence = in.nextLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (!stack.empty()) {
                    stack.pop();
                } else {
                    out.println("NO");
                    return;
                }
            }
        }
        if (stack.empty()) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
