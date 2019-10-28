package ru.mail.polis.ads.part5.KateMoreva;

import java.util.Scanner;

//e-olymp problem 991 "Шаблон и слово"

public class Task4 {
    private Task4(){
    }
    public static void main(String[] args){
        final Scanner input = new Scanner(System.in);
        final String str1 = input.nextLine();
        final String str2 = input.nextLine();
        final boolean[][] d = new boolean[str1.length() + 1][str2.length() + 1];
        d[0][0] = true;
        for (int i = 1; i <= str1.length(); ++i) {
            final char c1 = str1.charAt(i - 1);
            for (int j = 1; j <= str2.length(); ++j) {
                final char c2 = str2.charAt(j - 1);
                if (c1 == c2 || c1 == '?' || c2 == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (c1 == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i][j - 1] || d[i - 1][j];
                } else if (c2 == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1];
                }
            }
        }
        System.out.println(d[str1.length()][str2.length()] ? "YES" : "NO");
    }
}
