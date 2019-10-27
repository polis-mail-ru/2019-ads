package ru.mail.polis.ads.part5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem5 {

    private static BufferedWriter writer;
    private static int[] arr;

    private static void solve() throws IOException {

        int n = new Scanner(System.in).nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        while (true) {
            printArr();

            int j = arr.length - 2;
            while (j != -1 && arr[j] >= arr[j + 1]) {
                j--;
            }
            if (j == -1) {
                break;
            }

            int k = arr.length - 1;
            while (arr[j] >= arr[k]) {
                k--;
            }
            swap(j, k);
            int l = j + 1;
            int r = arr.length - 1;
            while (l < r) {
                swap(l++, r--);
            }
        }
    }

    private static void swap(int i, int j) {
        int s = arr[i];
        arr[i] = arr[j];
        arr[j] = s;
    }

    private static void printArr() throws IOException {
        for (int item : arr) {
            writer.write(item + " ");
        }
        writer.write("\n");
    }

    public static void main(String[] args) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
            solve();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
