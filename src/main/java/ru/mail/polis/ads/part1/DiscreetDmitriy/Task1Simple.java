package ru.mail.polis.ads.part1.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1Simple {
    private Task1Simple() {}

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);

        int a = new Scanner(System.in).nextInt();

        out.println(a / 10 + " " + a % 10);
        out.close();
    }
}

//  https://www.e-olymp.com/ru/submissions/5855647

