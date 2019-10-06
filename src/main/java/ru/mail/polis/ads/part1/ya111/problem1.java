package ru.mail.polis.ads.part1.ya111;

import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5735369
public final class problem1 {
    private problem1(){
    }

    public static void main(final String[] argc) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.print(num / 10 + " " +  num % 10);
    }
}
