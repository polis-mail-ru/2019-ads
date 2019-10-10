package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


/**
 * submission - https://www.e-olymp.com/ru/submissions/5825771
 */
public final class Problem4 {

    private static BigInteger[] arr;

    private Problem4() {
        // Should not be instantiated
    }

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[] tokens = reader.readLine().split(" ");
        arr = new BigInteger[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = new BigInteger(tokens[i]);
        }

        BigInteger stat = getOrderStatistics(n);
        System.out.println(stat);
    }

    private static BigInteger getOrderStatistics(int n) {

        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int mid = partition(left, right);

            if (mid == n - 1) {
                return arr[mid];
            }

            if (mid > n - 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int partition(int left, int right) {
        BigInteger control = arr[left];
        int i = left;
        int j = right;
        while (true) {
            while (arr[i].compareTo(control) > 0) {
                i++;
            }
            while (arr[j].compareTo(control) < 0) {
                j--;
            }
            if (i >= j) {
                return j;
            }

            BigInteger temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}