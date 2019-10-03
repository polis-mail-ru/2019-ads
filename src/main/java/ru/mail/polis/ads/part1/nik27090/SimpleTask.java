package ru.mail.polis.ads.part1.nik27090;


import java.io.PrintWriter;
import java.util.Scanner;

/*
* Задача: Простоя задача
* Решение: https://www.e-olymp.com/ru/submissions/5769024
*/

public final class SimpleTask {

    private SimpleTask(){

    }

    public static void main(final String[] args){
        final Scanner sc = new Scanner(System.in);
        final int num= sc.nextInt();
        try (PrintWriter out = new PrintWriter(System.out)) {
            div(num, out);
        }
    }

    static void div(final int num, final PrintWriter out){
         out.println(num/10+" "+num%10);
    }
}
