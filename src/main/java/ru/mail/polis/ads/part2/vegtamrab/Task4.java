package ru.mail.polis.ads.part2.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * Problem solution template.
 */
public final class Task4 {
    
    private static void solve(final Scanner in, final PrintWriter out) {
        String sequence = in.nextLine();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < sequence.length(); ++i) {
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
