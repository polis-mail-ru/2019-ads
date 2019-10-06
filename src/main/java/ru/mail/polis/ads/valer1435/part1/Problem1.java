package ru.mail.polis.ads.valer1435.part1;
// https://www.e-olymp.com/ru/submissions/5789725
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        System.out.println(String.valueOf(s/10) + " " + String.valueOf(s % 10));
    }
}