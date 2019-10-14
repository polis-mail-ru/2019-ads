package ru.mail.polis.ads.part2.ya111;

import java.util.Scanner;
public final class problem3837 {
    private problem3837(){
    }

    private static int pos;
    private static int max;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String s;
        StringBuilder[] bArray;
        for (int i = 0; i < t; ++i) {
            s = sc.next();
            max = 0;
            pos = s.length() - 1;
            bArray = new StringBuilder[(s.length() / 2) + 1];
            bTree(s, 0, bArray);
            for (int j = max - 1; j >= 0; j--)
                bArray[max].append(bArray[j]);

            System.out.println(bArray[max].toString());
        }
    }

    private static void bTree(String s, int level, StringBuilder[] bArray) {
        if (bArray[level] == null)
            bArray[level] = new StringBuilder();
        bArray[level].append(s.charAt(pos));
        pos--;
        max = Math.max(level, max);
        if (s.charAt(pos + 1) >= 'A' && s.charAt(pos + 1) <= 'Z') {
            bTree(s, level + 1, bArray);
            bTree(s, level + 1, bArray);
        }
    }
}