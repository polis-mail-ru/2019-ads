package ru.mail.polis.ads.part4.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 3737 "Куча ли?"

public class Task1 {
    private Task1(){
    }
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        final PrintWriter output = new PrintWriter(System.out);
        final int n = input.nextInt();

        final long[] heap = new long[n + 1];
        heap[1] = input.nextInt();
        boolean isHeap = true;

        for (int i = 2; i <= n; ++i) {
            heap[i] = input.nextLong();
            if (isHeap && heap[i] < heap[i / 2]) {
                isHeap = false;
            }
        }
        output.println(isHeap ? "YES" : "NO");
        output.close();
    }
}
