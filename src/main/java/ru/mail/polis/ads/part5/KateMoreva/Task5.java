package ru.mail.polis.ads.part5.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 2169 "Перестановки"

public class Task5 {
    private Task5(){
    }
    private static int n;
    private static int[] mass;

    private static void solve() {
        Scanner input = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        n = input.nextInt();
        mass = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = i + 1;
        }

        do {
            for (int number : mass) {
                out.print(number + " ");
            }
            out.println();
        } while (change());
        out.close();
    }

    private static boolean change() {
        int i = n - 2;
        while (i != -1 && mass[i] > mass[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false;
        }

        int j = n - 1;
        while (mass[i] > mass[j]) {
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
        int temp = mass[left];
        mass[left] = mass[rigth];
        mass[rigth] = temp;
    }

    public static void main(String[] args) {
        solve();
    }
}

