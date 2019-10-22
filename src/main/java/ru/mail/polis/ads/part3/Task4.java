package ru.mail.polis.ads.part3;

import java.io.*;
import java.math.BigInteger;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static BigInteger[] arr;

    private static BigInteger getOrderStatistics(int k) {
        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int mid = partition(left, right);

            if (mid == k - 1) {
                return arr[mid];
            }

            if (mid > k - 1) {
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

    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[] tokens = reader.readLine().split(" ");
        arr = new BigInteger[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = new BigInteger(tokens[i]);
        }

        System.out.println(getOrderStatistics(n));
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
