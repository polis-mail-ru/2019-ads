package ru.mail.polis.ads.part3.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olimp problem 1462 "Хитрая сортировка"

public class Task2 {
    private Task2(){
    }
    private static int[] mas;

    private static void sort(){
        for (int i = 0; i < mas.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < mas.length; ++j) {
                if (compare(j, minIndex)) {
                    minIndex = j;
                }
            }
            int tmp = mas[i];
            mas[i] = mas[minIndex];
            mas[minIndex] = tmp;
            mas[minIndex] = tmp;
        }
    }

    private static boolean compare(int j, int minIndex){
        if (mas[j] % 10 == mas[minIndex] % 10) {
            return mas[j] < mas[minIndex];
        } else {
            return mas[j] % 10 < mas[minIndex] % 10;
        }
    }


    private static void solve(final PrintWriter output){
        Scanner input = new Scanner(System.in);
        final int n = input.nextInt();
        mas = new int[n];

        for (int i = 0; i < n; i++) {
            mas[i] = input.nextInt();
        }
        sort();
        for (int i = 0; i < n; i++) {
            output.print(mas[i] + " ");
        }

    }

    public static void main(String[] args){
        try (PrintWriter output = new PrintWriter(System.out)) {
            solve(output);
        }
    }

}
