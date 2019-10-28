package ru.mail.polis.ads.part4.KateMoreva;

import java.util.Scanner;

//e-olymp problem 5149 "Коровы - в стойла"

public class Task7 {
    private Task7(){
    }
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        final int n = Integer.parseInt(input.next());
        final int k = Integer.parseInt(input.next());
        final int[] mas = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            mas[i] = Integer.parseInt(input.next());
        }
        int left = 0;
        int right = mas[n] - mas[1];
        if (k == 2) {
            System.out.println(right);
            return;
        }
        while (left != right) {
            int dist = (left + right) / 2;
            int count = 1;
            int lastCowIndex = 1;
            for (int i = 2; i <= n; ++i) {
                if (mas[i] - mas[lastCowIndex] >= dist) {
                    ++count;
                    lastCowIndex = i;
                }
            }
            if (count >= k) {
                left = dist + 1;
            } else {
                right = dist;
            }
        }
        System.out.println(left - 1);
    }
}

