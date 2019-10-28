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

            System.out.println(countInversions(0, n - 1));
        }
    }

    private static int countInversions(int left, int right) {
        if (left >= right) {
            return 0;
        }
        int invCount = 0;
        int mid = (left + right) / 2;
        invCount += countInversions(left, mid);
        invCount += countInversions(mid + 1, right);

        int[] temp = new int[right - left + 1];
        int left1 = left;
        int right1 = mid + 1;
        int i = 0;
        while (left1 <= mid && right1 <= right) {
            if (arr[left1] < arr[right1]) {
                temp[i++] = arr[left1++];
            } else {
                temp[i++] = arr[right1++];
                invCount += mid - left1 + 1;
            }
        }
        while (left1 <= mid) {
            temp[i++] = arr[left1++];
        }
        while (right1 <= right) {
            temp[i++] = arr[right1++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
        return invCount;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
