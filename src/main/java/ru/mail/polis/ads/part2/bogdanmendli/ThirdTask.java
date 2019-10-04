package ru.mail.polis.ads.part2.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ThirdTask {

    private static int index;
    private static String[] result;
    private static char[] expression;

    private static void solve(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(in.readLine());
            for (int i = 0; i < t; i++) {
                final String str = in.readLine();
                expression = str.toCharArray();
                result = new String[expression.length];
                Arrays.fill(result, "");
                index = expression.length - 1;
                fillResult(0);
                print();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillResult(final int level) {
        result[level] += expression[index];
        index--;
        if (Character.isUpperCase(expression[index + 1])) {
            fillResult(level + 1);
            fillResult(level + 1);
        }
    }

    private static void print() {
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve();
    }
}