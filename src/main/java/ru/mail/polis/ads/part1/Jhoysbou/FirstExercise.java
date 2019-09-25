package ru.mail.polis.ads.part1.Jhoysbou;

import java.util.Scanner;
// submition here https://www.e-olymp.com/ru/submissions/5714850
public class FirstExercise{
    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        doubleDigitSeparator(number);
    }

    private static void doubleDigitSeparator(int number){
        System.out.print(number / 10 + " " + number % 10);
    }
}
