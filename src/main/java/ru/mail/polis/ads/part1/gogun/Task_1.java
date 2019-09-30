package ru.mail.polis.ads.part1.gogun;

import java.util.Scanner;

public class Task_1 {
    private static void solve(int num) {
        int num1 = num / 10;
        int num2 = num % 10;

        System.out.print(num1 + " " + num2);
    }

    public static void main(String[] argc) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();

        solve(num);
    }
}
