package ru.mail.polis.ads.part1.gogun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {
    private static void solve(final BufferedReader bufferedReader) throws IOException {
    String s =  bufferedReader.readLine();
        int n = s.length();
        String packing[][] = new String[n][n];
        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                String min = s.substring(left, left + len);
                if (len > 4) {
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String tmp = packing[left][right1] + packing[left2][right];
                        if (tmp.length() < min.length()) {
                            min = tmp;
                        }
                    }
                    for (int p = 1; p < len; p++) {
                        if (len % p == 0){
                            boolean isPeriod = true;
                            for (int i = left + p; i <= right; i++) {
                                char[] chr_s = s.toCharArray();
                                if (chr_s[i] != chr_s[i - p]) {
                                    isPeriod = false;
                                    break;
                                }
                            }
                            if (isPeriod) {
                                String tmp = len / p + "(" + packing[left][left + p - 1] + ")";
                                if (tmp.length() < min.length()) {
                                    min = tmp;
                                }
                            }
                        }
                    }
                }
                packing[left][right] = min;
            }
        }
        System.out.println(packing[0][n - 1]);
    }

    public static void main(String[] args) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            solve(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
