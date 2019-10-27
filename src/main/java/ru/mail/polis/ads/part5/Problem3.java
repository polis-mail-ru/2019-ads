package ru.mail.polis.ads.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5937267
 */
public class Problem3 {

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        long[] numbers = new long[n];
        String[] input = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(input[i]);
        }
        int result = 0;
        int[] lengthSubsequences = new int[n];
        lengthSubsequences[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (numbers[j] != 0 && numbers[i] % numbers[j] == 0 && lengthSubsequences[j] > max) {
                    max = lengthSubsequences[j];
                }
            }
            lengthSubsequences[i] = max + 1;
            if (lengthSubsequences[i] > result) {
                result = lengthSubsequences[i];
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
