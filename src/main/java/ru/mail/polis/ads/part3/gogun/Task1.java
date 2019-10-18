package ru.mail.polis.ads.part3.gogun;

import java.io.*;
import java.util.*;

public class Task1 {

    private static void sort(int[] array, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] leftPart = new int[mid];
        int[] rightPart = new int[n-mid];

        for (int i = 0; i < mid; i++) {
            leftPart[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            rightPart[i-mid] = array[i];
        }
        sort(leftPart, mid);
        sort(rightPart, n - mid);

        merge(array, leftPart, rightPart, mid, n - mid);
    }

    public static void merge(int[] array, int[] leftPart, int[] rightPart, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftPart[i] <= rightPart[j]) {
                array[k++] = leftPart[i++];
            }
            else {
                array[k++] = rightPart[j++];
            }
        }
        while (i < left) {
            array[k++] = leftPart[i++];
        }
        while (j < right) {
            array[k++] = rightPart[j++];
        }
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int n = Integer.parseInt(input.readLine());

            int[] array = new int[n];

            int i=0;

            for (String a: input.readLine().split(" ")){
                if (i == n) {
                    break;
                }
                array[i] = Integer.parseInt(a);
                ++i;
            }



            sort(array, array.length);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; ++j) {
                sb.append(array[j]);
                sb.append(" ");
            }

            output.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}