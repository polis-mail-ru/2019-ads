package ru.mail.polis.ads.part1.Jhoysbou;

import java.util.Scanner;

// submission here https://www.e-olymp.com/ru/submissions/5714850

public abstract class FirstExercise{
    public static void main(final String... args){
        final Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();
        doubleDigitSeparator(number);
    }

    private static void doubleDigitSeparator(final int number){
        System.out.print(number / 10 + " " + number % 10);
    }
}
