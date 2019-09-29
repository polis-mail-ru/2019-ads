package ru.mail.polis.ads.part1.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int a = in.nextInt();
        int b = a / 10;
        int c = a % 10;

        out.println(b + " " + c);

        out.close();
    }
}
