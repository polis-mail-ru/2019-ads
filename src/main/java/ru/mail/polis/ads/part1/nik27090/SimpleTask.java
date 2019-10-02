package ru.mail.polis.ads.part1.nik27090;

import java.util.Scanner;

public class SimpleTask {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num= sc.nextInt();
        div(num);
    }

    static void div(int num){
         System.out.println(num/10+" "+num%10);
    }
}
