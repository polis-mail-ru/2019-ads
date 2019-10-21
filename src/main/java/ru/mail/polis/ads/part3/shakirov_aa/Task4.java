package ru.mail.polis.ads.part3.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine());

        String[] numbers = bf.readLine().split(" ");
        int length = numbers.length;
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            array[i] = new BigInteger(numbers[i]);
        }

        System.out.println(findOrderStatistic(array, array.length - k));
    }

    static BigInteger findOrderStatistic (BigInteger[] array, int k) {
        int left = 0, right = array.length;
        while (true) {
            int mid = partition(array, left, right);

            if (mid == k) {
                return array[mid];
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    static int partition (BigInteger[] arr, int left, int right) {
        BigInteger x = arr[left];
        int j = left;
        for (int i = left + 1; i < right; i++) {
            if (arr[i].compareTo(x) <= 0) {
                j++;
                BigInteger temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        BigInteger temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;

        return j;
    }
}