package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FourthTask {

    private static int n;
    private static int[] numbers;
    private static int inversions;

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(in.readLine());
        String[] input = in.readLine().split(" ");
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        inversions = 0;
        merge(0, n - 1);
        System.out.println(inversions);
    }

    private static void merge(int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        merge(start, mid);
        merge(mid + 1, end);

        final int n = end - start;
        int[] temp = new int[n + 1];
        System.arraycopy(numbers, start, temp, 0, n + 1);

        int left = 0;
        int right = n / 2 + 1;

        for (int i = start; i <= end; i++) {
            if (left > n / 2) {
                numbers[i] = temp[right++];
            } else if (right > n) {
                numbers[i] = temp[left++];
            } else if (temp[right] < temp[left]) {
                inversions += n / 2 - left + 1;
                numbers[i] = temp[right++];
            } else {
                numbers[i] = temp[left++];
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
