package ru.mail.polis.ads.part4;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task7 {
    private Task7() {
        // Should not be instantiated
    }

    private static int n;
    private static int k;
    private static int[] arr;


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        String[] str = in.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        str = in.readLine().split(" ");
        int l, r, m;
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(str[i]);
        }

        System.out.println(getMaxDistance());
    }

    private static int getMaxDistance() {
        if (k == 2) {
            return arr[n - 1] - arr[0];
        }

        int left = 0;
        int right = arr[n - 1];
        while (left != right) {
            int mid = (left + right) / 2;
            int g = 1;
            int j = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] - arr[j] > mid) {
                    j = i;
                    g++;
                }
            }

            if (g >= k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
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