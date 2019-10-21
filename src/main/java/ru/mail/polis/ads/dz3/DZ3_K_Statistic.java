package ru.mail.polis.ads.dz3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class DZ3_K_Statistic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        BigInteger[] arr = new BigInteger[s.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new BigInteger(s[i]);
        }
        int low = 0;
        int hight = arr.length - 1;
        findKStatistic(arr, low, hight);
        System.out.println(arr[arr.length - k]);
    }

    private static void findKStatistic(BigInteger[] arr, int low, int high) {
        if (arr.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        BigInteger opora = arr[middle];

        int i = low, j = high;
        while (i <= j) {
            while (arr[i].compareTo(opora) < 0) {
                i++;
            }

            while (arr[j].compareTo(opora) > 0) {
                j--;
            }

            if (i <= j) {
                BigInteger temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            findKStatistic(arr, low, j);

        if (high > i)
            findKStatistic(arr, i, high);
    }
}
