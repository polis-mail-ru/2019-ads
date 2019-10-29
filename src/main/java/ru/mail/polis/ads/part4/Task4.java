package ru.mail.polis.ads.part4;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static int[] arr;

    private static int mergeSort(int left, int right) {

        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        int invCount = 0;
        invCount += mergeSort(left, mid);
        invCount += mergeSort(mid + 1, right);


        int[] temp = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[i++] = arr[l++];
            } else {
                temp[i++] = arr[r++];
                invCount += mid - l + 1;
            }
        }

        while (l <= mid) {
            temp[i++] = arr[l++];
        }
        while (r <= mid) {
            temp[i++] = arr[r++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
        return invCount;
    }

    private static void solve() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] tokens = reader.readLine().split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }

            System.out.println(mergeSort(0, n - 1));
        }
    }


    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}