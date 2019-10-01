package ru.mail.polis.ads.part1.gogun;

import java.util.Scanner;

public class Task2 {
    private Task2(){
    }

    static int[][] split;
    static int[][] din;

    static void make(String str, int n) {
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    din[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (str.charAt(i) == '(' && str.charAt(j) == ')' || str.charAt(i) == '[' && str.charAt(j) == ']') {
                    min = din[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    if (din[i][k] + din[k + 1][j] < min) {
                        min = din[i][k] + din[k + 1][j];
                        splitMin = k;
                    }
                }
                din[i][j] = min;
                split[i][j] = splitMin;
            }
        }
    }

    static void restore(final int i, final int j, final String s) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
                default:
                    break;
            }
            return;
        }
        if (din[i][j] == 0) {
            System.out.print(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s);
            System.out.print(s.charAt(j));
            return;
        }
        int kk = split[i][j];
        restore(i, kk, s);
        restore(kk + 1, j, s);
    }

    public static void main(final String[] argc) {
        Scanner scan = new Scanner(System.in);
        final String str = scan.nextLine();
        if (str.isEmpty()) {
            System.out.println();
            return;
        }
        final int num = str.length();
        din = new int[num][num];
        split = new int[num][num];
        make(str, num);
        restore(0, num - 1, str);
    }
}



