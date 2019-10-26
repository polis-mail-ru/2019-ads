package ru.mail.polis.ads.lectionWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FourInLection {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String val = in.readLine(), pattern = in.readLine(), swap;
        if (val.contains("?") || val.contains("*")) {
            swap = pattern;
            pattern = val;
            val = swap;
        }
        boolean[][] truth = new boolean[val.length() + 1][pattern.length() + 1];
        truth[0][0] = true;
        for (int i = 1; i <= val.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (val.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    truth[i][j] = truth[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    truth[i][j] = truth[i - 1][j] || truth[i - 1][j - 1] || truth[i][j - 1];
                } else {
                    truth[i][j] = false;
                }
            }
        }
        if (truth[val.length()][pattern.length()]){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
