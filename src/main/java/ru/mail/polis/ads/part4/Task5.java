package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  submission - https://www.e-olymp.com/ru/submissions/5899773
 */
public class Task5 {

    private static int[] arr;
    private static int m;

    private static void solve() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] tokens = reader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            tokens = reader.readLine().split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }

            if (isPossible(0, n - 1)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean isPossible(int left, int right) {
        if (left >= right) {
            return true;
        }

        int mid = (left + right) / 2;
        if (!isPossible(left, mid) || !isPossible(mid + 1, right)) {
            return false;
        }

        int[] temp = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[i++] = arr[l++];
            } else {
                if (arr[mid] + arr[r] > m) {
                    return false;
                }
                temp[i++] = arr[r++];
            }
        }
        while (l <= mid) {
            temp[i++] = arr[l++];
        }
        while (r <= right) {
            temp[i++] = arr[r++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
        return true;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}