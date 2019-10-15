package ru.mail.polis.ads;

import java.util.Scanner;

class DZ2_Twelve {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] stair = new int[n + 2];
        stair[0] = 0;
        stair[n + 1] = 0;

        for (int i = 1; i < n + 1; i++) {
            stair[i] = in.nextInt();
        }

        int step = in.nextInt();

        if (step == 1) {
            int sum = 0;
            for (int i = 1; i < n + 1; i++) {
                sum += stair[i];
            }
            System.out.println(sum);
        }

        for (int i = 1; i < n + 2; i++) {
            stair[i] += getMax(stair, i - step, i);
        }
        System.out.println(stair[n + 1]);
    }

    private static int getMax(int[] stair, int left, int right) {
        if (left < 0) {
            left = 0;
        }
        int max = stair[left];
        for (int i = left + 1; i < right; i++) {
            if (stair[i] > max)
                max = stair[i];
        }
        return max;
    }
}
