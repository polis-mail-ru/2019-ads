package ru.mail.polis.ads.lectionWork;

import java.io.*;

public class TwoInLesction {
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int coming = Integer.parseInt(in.readLine());
        int[] arr = new int[coming];
        for (int i = 0; i < coming; i++) {
            arr[i] = i + 1;
            out.write(arr[i] + " ");
        }
        out.write("\n");
        logic(arr);
        out.flush();
    }

    private static void logic(int[] arr) throws IOException {
        int k = arr.length - 1;
        while (k != 0) {
            if (k >= 1 && arr[k - 1] < arr[k]) {
                int left = k - 1;
                replaceSubArr(left, arr);
                k = arr.length - 1;
                for (int i = 0; i < arr.length; i++) {
                    out.write(arr[i] + " ");
                }
                out.write("\n");
            } else {
                k--;
            }
        }
    }

    private static void replaceSubArr(int left, int[] arr) {
        int i = arr.length - 1;
        int swap;
        while (true) {
            if (arr[left] < arr[i]) {
                swap = arr[left];
                arr[left] = arr[i];
                arr[i] = swap;
                bubleSort(arr, left + 1);
                break;
            } else {
                i--;
            }
        }
    }

    public static void bubleSort(int[] arr, int left) {
        int swap;
        for (int i = left; i < arr.length; i++) {
            for (int j = left; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    swap = arr[i];
                    arr[i] = arr[j];
                    arr[j] = swap;
                }
            }
        }
    }
}