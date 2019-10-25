package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  submission - https://www.e-olymp.com/ru/submissions/5885983
 */
public class Problem4 {

    private static int[] arr;

    private static void solve() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] tokens = reader.readLine().split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }

            mergeSort(0, n);
        }
    }

    private static void mergeSort(int left, int right) {
        if (left - right == 1) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid, right);

        int[] temp = new int[right - left];
        int left1 = left;
        int right1 = mid;
        int i = 0;
        while (left1 < mid && right1 < right) {
            if (arr[left1] < arr[right1]) {
                temp[i++] = arr[left1++];
            } else {
                temp[i++] = arr[right1++];
            }
        }
        while (left1 < mid) {
            temp[i++] = arr[left1++];
        }
        while (right1 < right) {
            temp[i++] = arr[right1++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
