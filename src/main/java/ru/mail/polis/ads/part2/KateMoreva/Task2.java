package ru.mail.polis.ads.part2.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 262 "Лесенка"

public class Task2 {

    private Task2(){
    }

    private static int max(int a, int b){
        return a > b ? a : b;
    }

    private static int solve(int mas[], int n, int k){
        int tmp, current;
        for (int i = 1; i <= n + 1; i++) {
            tmp = max(i - k, 0);
            current = mas[i];
            mas[i] += mas[tmp];
            for (int j = tmp + 1; j < i; j++) {
                mas[i] = max(mas[i], mas[j] + current);
            }
        }
        return mas[n + 1];
    }

    public static void main(final String[] arg){
        Scanner input = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = input.nextInt();
        int array[];
        array = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            array[i] = input.nextInt();
        }
        array[0] = array[n + 1] = 0;
        int k = input.nextInt();
        out.println(solve(array, n, k));
        out.flush();

    }
}
