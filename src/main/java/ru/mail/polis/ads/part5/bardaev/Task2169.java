package ru.mail.polis.ads.part5.bardaev;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task2169 {
    static PrintWriter out = new PrintWriter(System.out);

    public static void recurse(int[] newArray, int i) {
        for (int j = i; j < newArray.length; j++) {
            newArray = swap(newArray, i, j);
            if (i < (newArray.length - 1)) {
                recurse(newArray, i + 1);
            }
            if (i == (newArray.length - 1)) {
                display(newArray);
            }
        }
    }

    private static int[] swap(int[] a, int i, int j) {
        int[] newArray = a.clone();
        int temp = newArray[i];
        newArray[i] = newArray[j];
        newArray[j] = temp;
        return newArray;
    }

    public static void display(int[] Array) {
        for (int i : Array) {
            out.print(i+" ");
        }
        out.println();
        out.flush();
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i <= arr.length; i++) {
            arr[i-1] = i;
        }
        recurse(arr, 0);
    }
}
