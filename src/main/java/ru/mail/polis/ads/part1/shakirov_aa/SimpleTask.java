package ru.mail.polis.ads.part1.shakirov_aa;

import java.util.Scanner;

public class SimpleTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        if (number < 10 || number > 99) {
            throw new IllegalArgumentException();
        }

        System.out.print(number / 10 + " " + number % 10);
    }
}
