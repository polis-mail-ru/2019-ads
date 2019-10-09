package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * submission - https://www.e-olymp.com/ru/submissions/5819685
 */
public final class Problem1 {
    private static int[] arr;

    private Problem1() {
        // Should not be instantiated
    }

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().split(" ");

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        sort(0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void sort(final int left, final int right) {
        if (left >= right) {
            return;
        }

        int midIndex = (right + left) / 2;
        sort(left, midIndex);
        sort(midIndex + 1, right);

        int[] temp = new int[right - left + 1];
        int i = 0;
        int left1 = left;
        int left2 = midIndex + 1;
        while (left1 <= midIndex && left2 <= right) {
            if (arr[left2] < arr[left1]) {
                temp[i++] = arr[left2++];
            } else {
                temp[i++] = arr[left1++];
            }
        }
        while (left1 <= midIndex) {
            temp[i++] = arr[left1++];
        }
        while (left2 <= right) {
            temp[i++] = arr[left2++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
