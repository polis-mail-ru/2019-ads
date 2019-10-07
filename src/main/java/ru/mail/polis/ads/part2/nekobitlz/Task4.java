package ru.mail.polis.ads.part2.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        String str = in.nextLine();
        int openParentheses = 0;

        for (int i = 0; i < str.length(); i++) {
            if (openParentheses >= 0)
                switch (str.charAt(i)) {
                    case '(':
                        openParentheses++;
                        break;
                    case ')':
                        openParentheses--;
                        break;
                }
            else {
                out.println("NO");
                out.close();
                return;
            }
        }

        if (openParentheses != 0) {
            out.println("NO");
            out.close();
            return;
        }

        out.println("YES");
        out.close();
    }
}