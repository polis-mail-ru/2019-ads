package ru.mail.polis.ads.part2.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task4BracketsSequence {
    private Task4BracketsSequence() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        String string = in.nextLine();
        int correctBracketCounter = 0;

        for (int i = 0; i < string.length(); i++) {
            switch (string.charAt(i)) {
                case '(' :
                    correctBracketCounter++;
                    break;
                case ')' :
                    correctBracketCounter--;
                    break;
            }
            if (correctBracketCounter < 0) {
                out.println("NO");
                out.close();
                return;
            }
        }
        if (correctBracketCounter != 0) {
            out.println("NO");
            out.close();
            return;
        }

        out.println("YES");
        out.close();
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

