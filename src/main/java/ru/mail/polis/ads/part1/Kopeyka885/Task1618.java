package ru.mail.polis.ads.part1.Kopeyka885;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1618 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out);

        int num1 = scanner.nextInt();
        if (num1 == 0){
            System.out.println(0);
            return;
        }
        int[] a = new int[num1+1];
        for (int i = 0; i < num1; i++) {
            a[i+1] = scanner.nextInt();
        }
        int num2 = scanner.nextInt();
        if (num2 == 0){
            System.out.println(0);
            return;
        }
        int[] b = new int[num2+1];
        for (int i = 0; i < num2; i++) {
            b[i+1] = scanner.nextInt();
        }

        int f[][] = new int[num1 + 1][num2 + 1];

        for (int i = 1; i < num1 + 1; i++) {
            for (int j = 1; j < num2 + 1; j++) {
                if (a[i] == b[j]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        printWriter.print(f[num1][num2]);
        printWriter.flush();
    }
}