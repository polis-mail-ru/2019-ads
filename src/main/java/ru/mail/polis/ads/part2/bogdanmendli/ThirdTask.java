package ru.mail.polis.ads.part2.bogdanmendli;

import java.util.Arrays;
import java.util.Scanner;

public class ThirdTask {

    private static int index;
    private static String result[];
    private static char expression[];

    private static void fillResult(final int level) {
        result[level] += expression[index];
        index--;
        if (Character.isUpperCase(expression[index + 1])) {
            fillResult(level + 1);
            fillResult(level + 1);
        }
    }

    private static void solve(){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        String str;
        for (int i = 0; i < t; i++) {
            str = in.next();
            expression = str.toCharArray();
            result = new String[expression.length];
            Arrays.fill(result, "");
            index = expression.length - 1;
            fillResult(0);
            print(expression.length - 1, result);
        }
    }

    private static void print(final int length, String[] result) {
        for (int i = length; i >= 0; i--) {
            System.out.print(result[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve();
    }
}