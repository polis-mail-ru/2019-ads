package ru.mail.polis.ads.part5.bogdanmendli;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FifthTask {

    private static int n;
    private static int[] numbers;

    private static void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        n = in.nextInt();
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        do {
            for (int number : numbers) {
                out.print(number + " ");
            }
            out.println();
        } while (permutation());
        out.close();
    }

    private static boolean permutation() {
        int i = n - 2;
        while (i != -1 && numbers[i] > numbers[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false;
        }

        int j = n - 1;
        while (numbers[i] > numbers[j]) {
            j--;
        }
        swap(i, j);
        int left = i + 1;
        int right = n - 1;
        while (left < right) {
            swap(left++, right--);
        }
        return true;
    }

    private static void swap(int left, int rigth) {
        int temp = numbers[left];
        numbers[left] = numbers[rigth];
        numbers[rigth] = temp;
    }

    public static void main(String[] args) {
        solve();
    }
}
