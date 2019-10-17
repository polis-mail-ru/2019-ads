package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeventhTask {

    private static int n;
    private static int k;
    private static int[] coordinates;

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        coordinates = new int[n];
        input = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            coordinates[i] = Integer.parseInt(input[i]);
        }
        if (k == 2) {
            System.out.println(coordinates[n - 1] - coordinates[0]);
            return;
        }
        searchMaxDistance();

    }

    private static void searchMaxDistance() {
        int left = 0;
        int right = coordinates[n - 1] - coordinates[0];
        while (left != right) {
            int mid = (left + right) / 2;
            int kine = 1;
            int j = 0;
            for (int i = 1; i < n; i++) {
                if (coordinates[i] - coordinates[j] > mid) {
                    j = i;
                    kine++;
                }
            }

            if (kine >= k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}
