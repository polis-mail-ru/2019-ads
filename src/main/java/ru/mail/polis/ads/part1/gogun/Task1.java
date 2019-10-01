package ru.mail.polis.ads.part1.gogun;

import java.util.Scanner;

public final class Task1 {
    private Task1(){
    }

    private static void solve(final int num) {
        final int num1 = num / 10;
        final int num2 = num % 10;

        System.out.print(num1 + " " + num2);
    }

    public static void main(final String[] argc) {
        final Scanner scan = new Scanner(System.in);
        final int num = scan.nextInt();

        solve(num);
    }
}
