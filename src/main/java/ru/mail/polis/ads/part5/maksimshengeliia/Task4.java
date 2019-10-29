package ru.mail.polis.ads.part5.maksimshengeliia;

import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5972145
* */
public class Task4 {
    public static void main(String[] strings) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String second = sc.nextLine();
        String pattern, word;
        if (first.contains("*") || first.contains("?")) {
            pattern = first;
            word = second;
        } else {
            pattern = second;
            word = first;
        }
        boolean[][] d = new boolean[word.length() + 1][pattern.length() + 1];
        d[0][0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (word.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern.charAt(j-1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        System.out.println(d[word.length()][pattern.length()] ? "YES" : "NO");
    }
}


