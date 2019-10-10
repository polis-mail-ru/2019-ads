package ru.mail.polis.ads.part3.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class FourthTask {

    private static int k;

    private static void solve() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(in.readLine());
        String[] array = in.readLine().split(" ");
        final int n = array.length;
        BigInteger[] numbers = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = new BigInteger(array[i]);
        }

        searchMin(numbers, numbers.length);
    }

    private static void searchMin(BigInteger[] arr, int length) {
        if (length == 1) {
            System.out.println(arr[0]);
            return;
        }
        int indexBase = length / 2;
        BigInteger base = arr[indexBase];
        BigInteger[] left = new BigInteger[length - 1];
        BigInteger[] right = new BigInteger[length - 1];
        int indexRight = 0, indexLeft = 0;
        for (int i = 0; i < length; i++) {
            if (indexBase == i) {
                continue;
            }
            if (arr[i].compareTo(base) > 0) {
                left[indexLeft++] = arr[i];
            } else {
                right[indexRight++] = arr[i];
            }
        }
        if (indexLeft >= k) {
            searchMin(left, indexLeft);
        } else if (k - indexLeft == 1) {
            System.out.println(base);
        } else {
            k = k - indexLeft - 1;
            searchMin(right, indexRight);
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
