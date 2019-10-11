package ru.mail.polis.ads.part1.KateMoreva;

import java.util.Scanner;

//e-olymp problem 1

public class Task1 {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println((number / 10)+" "+(number % 10));
    }
}

