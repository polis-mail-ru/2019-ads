package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DZ4_Heap {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        long[] arr = new long[n];
        String[] nums = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(nums[i]);
        }

        if (checkForHeap(n, arr)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkForHeap(int n, long[] arr) {
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
}
