package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  submission - https://www.e-olymp.com/ru/submissions/5882111
 */
public class Problem1 {

    private static long[] arr;

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        arr = new long[n];
        String[] tokens = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(tokens[i]);
        }

        if (checkForHeap(n)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkForHeap(int n) {
        for (int i = 1; 2 * i <= n; i++) {
            if (arr[i - 1] > arr[2 * i - 1]) {
                return false;
            }
            if (2 * i + 1 <= n && arr[i - 1] > arr[2 * i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
