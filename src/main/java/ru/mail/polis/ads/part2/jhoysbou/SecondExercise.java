package ru.mail.polis.ads.part2.jhoysbou;

import java.util.Scanner;

public class SecondExercise {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int length = scanner.nextInt();
        int[] values = new int[length + 2];
        scanner.nextLine();
        String string = scanner.nextLine();
        String[] temp = string.split(" ");

        for (int i = 0; i < length + 2; i++) {
            if (i == 0 || i == length + 1) {
                values[i] = 0;
            } else {
                values[i] = Integer.parseInt(temp[i-1]);
            }
        }
        final int maxStep = scanner.nextInt();

        System.out.println(getMaxScore(values, maxStep)); // Noncomplying

    }

    private static int getMaxScore(int[] values, int maxStep) {
        int length = values.length;
        for (int i = 1; i < length; i++) {
            int maxValue = -1001;
            for (int j = i - 1; j >= 0 && j >= i - maxStep; j--) {
                if (values[j] + values[i] > maxValue) {
                    maxValue = values[j] + values[i];
                }
            }
            values[i] = maxValue;
        }
        return values[length-1];
    }
 }
