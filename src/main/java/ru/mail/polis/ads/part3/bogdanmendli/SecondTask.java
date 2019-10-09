package ru.mail.polis.ads.part3.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SecondTask {

    private static void solve() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(in.readLine());
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(array, (o1, o2) -> {
            if (o1 % 10 > o2 % 10) {
                return 1;
            } else if (o1 % 10 < o2 % 10) {
                return -1;
            } else {
                return o1 - o2;
            }
        });
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
