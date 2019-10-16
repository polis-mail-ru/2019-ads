package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {

    private static void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            final int n = Integer.parseInt(in.readLine());
            long[] numbers = new long[n + 1];
            String[] input = in.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                numbers[i] = Long.parseLong(input[i - 1]);
            }

            for (int i = 1; i <= n / 2; i++) {
                if (numbers[i] > numbers[i * 2]) {
                    System.out.println("NO");
                    return;
                }
                if (i * 2 + 1 <= n && numbers[i] > numbers[i * 2 + 1]) {

                    System.out.println("NO");
                    return;

                }
            }

            System.out.println("YES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
