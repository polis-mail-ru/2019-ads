package ru.mail.polis.ads.part3.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FirstTask {

    private static int[] array;

    private static void solve() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(in.readLine());
        array = new int[n];
        String[] numbers = in.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }

        sort(0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static void sort(final int start, final int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(start, mid);
        sort(mid + 1, end);

        final int n = end - start;
        int[] temp = new int[n + 1];
        System.arraycopy(array, start, temp, 0, n + 1);

        int leftArray = 0;
        int rightArray = n / 2 + 1;

        for (int i = start; i <= end; i++) {

            if (leftArray > n / 2) {
                array[i] = temp[rightArray++];
            } else if (rightArray > n) {
                array[i] = temp[leftArray++];
            } else if (temp[leftArray] > temp[rightArray]) {
                array[i] = temp[rightArray++];
            } else {
                array[i] = temp[leftArray++];
            }
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
