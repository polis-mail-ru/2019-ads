package ru.mail.polis.ads.part5.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondTask {

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        long w = Long.parseLong(input[0]);
        long h = Long.parseLong(input[1]);
        long n = Long.parseLong(input[2]);

        long left = 0;
        long right = n * Math.max(h, w);
        do {
        long mid = (left + right) / 2;
        long v = (mid / w) * (mid / h);
            if (v >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } while (left < right);
        System.out.println(left);
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
