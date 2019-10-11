package ru.mail.polis.ads.task3.art241111;

import java.io.*;
import java.math.BigInteger;
import static java.lang.System.exit;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5834119
 */

public class kthOrdinalStatistics {


    private static void solve(BufferedReader in) throws IOException {
        int n = Integer.parseInt(in.readLine());

        String[] array = in.readLine().split(" ");
        int length = array.length;
        BigInteger[] numbers = new BigInteger[length];

        int i = 0;
        for (String str:array) {
            numbers[i] = new BigInteger(str);
            i++;
        }

        searchValue(numbers, numbers.length, n);
    }


    private static void searchValue(BigInteger[] arr, int length, int n) {
        int indexOfBase = (int)(Math.random() * (length - 1));

        BigInteger base = arr[indexOfBase];
        BigInteger[] left = new BigInteger[length - 1];
        BigInteger[] right = new BigInteger[length - 1];

        // partition
        int indexR = 0;
        int indexL = 0;

        for (int i = 0; i < length; i++) {
            if (indexOfBase == i) {
                continue;
            }
            if (arr[i].compareTo(base) <= 0) {
                right[indexR++] = arr[i];
            } else {
                left[indexL++] = arr[i];
            }
        }


        if (indexL >= n) {
            searchValue(left, indexL, n);
        } else if (n - indexL == 1) {
            System.out.println(base);
            exit(0);
        } else {
            n = n - indexL - 1;
            searchValue(right, indexR, n);
        }
    }

    public static void main(String[] args) {
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
            solve(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}