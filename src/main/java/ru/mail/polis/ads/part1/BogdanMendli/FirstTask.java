package ru.mail.polis.ads.part1.BogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public class FirstTask {

    private static void solve(Scanner in, PrintWriter out) {
        int result = in.nextInt();
        out.println(result / 10 + " " + result % 10);
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            solve(in, out);
        }
    }
}
