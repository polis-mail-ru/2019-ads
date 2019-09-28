package ru.mail.polis.ads.part1.BogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public class FourthTask {

    private static void solve(Scanner in, PrintWriter out) {
        int lengthFirstSequence = in.nextInt();
        int[] firstSequence = new int[lengthFirstSequence + 1];
        for (int i = 1; i <= lengthFirstSequence ; i++) {
            firstSequence[i] = in.nextInt();
        }

        int lengthSecondSequence = in.nextInt();
        int[] secondSequence = new int[lengthSecondSequence + 1];
        for (int i = 1; i <= lengthSecondSequence ; i++) {
            secondSequence[i] = in.nextInt();
        }

        int[][] mas = new int[2][lengthSecondSequence + 1];

        for (int i = 1; i <= lengthFirstSequence ; i++) {
            for (int j = 1; j <= lengthSecondSequence; j++) {
                if (firstSequence[i] == secondSequence[j]) {
                    mas[i % 2][j] = 1 + mas[(i - 1) % 2][j - 1];
                } else {
                    mas[i % 2][j] = Math.max(mas[(i - 1) % 2][j], mas[i % 2][j - 1]);
                }
            }
        }
        out.println(mas[lengthFirstSequence % 2][lengthSecondSequence]);
    }

    public static void main(String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
