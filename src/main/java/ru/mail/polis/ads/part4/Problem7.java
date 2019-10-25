package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem7 {

    private static int n;
    private static int k;
    private static int[] coordinates;

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);
        coordinates = new int[n];
        tokens = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            coordinates[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(maxDistance());

    }

    private static int maxDistance() {
        if (k == 2) {
            return coordinates[n - 1] - coordinates[0];
        }
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
        return left;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
