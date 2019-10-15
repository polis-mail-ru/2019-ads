package ru.mail.polis.ads;

import java.util.Scanner;

class CharSec {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String incoming = in.nextLine();
        int n = incoming.length();
        int[][] tablet = new int[n][n];
        int[][] tabletOfSplit = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    tablet[j][i] = 1;
                    continue;
                }
                int min = 999999999;
                int splitMin = -1;
                if ((incoming.charAt(j) == '(' && incoming.charAt(i) == ')')
                        || (incoming.charAt(j) == '[' && incoming.charAt(i) == ']')) {
                    min = tablet[j + 1][i - 1];
                }
                for (int k = j; k < i; k++) {
                    if (tablet[j][k] + tablet[k + 1][i] < min) {
                        min = tablet[j][k] + tablet[k + 1][i];
                        splitMin = k;
                    }
                }
                tablet[j][i] = min;
                tabletOfSplit[j][i] = splitMin;
            }
        }
        logic(0, n - 1, incoming, tablet, tabletOfSplit);
    }

    public static void logic(int j, int i, String incoming, int[][] tablet, int[][] tabletOfSplit) {
        if (j > i) {
            return;
        }
        if (j == i) {
            if (incoming.charAt(j) == '(' || incoming.charAt(j) == ')') {
                System.out.print("()");
            } else {
                System.out.print("[]");
            }
            return;
        }
        if (tablet[j][i] == 0) {
            System.out.print(incoming.substring(j, i + 1));
            return;
        }
        if (tabletOfSplit[j][i] == -1) {
            System.out.print(incoming.charAt(j));
            logic(j + 1, i - 1, incoming, tablet, tabletOfSplit);
            System.out.print(incoming.charAt(i));
            return;
        }
        int thisElem = tabletOfSplit[j][i];
        logic(j, thisElem, incoming, tablet, tabletOfSplit);
        logic(thisElem + 1, i, incoming, tablet, tabletOfSplit);
    }
}
