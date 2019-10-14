package ru.mail.polis.ads.part1.kara111;

import java.util.Scanner;
public class GeneralSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstLength = scanner.nextInt();
        int[] subsequenceOne = new int[firstLength + 1];
        for (int i = 1; i <= firstLength; i++) {
            subsequenceOne[i] = scanner.nextInt();
        }


        int secondLength = scanner.nextInt();
        int[] secondSequence = new int[secondLength + 1];
        for (int i = 1; i <= secondLength; i++) {
            secondSequence[i] = scanner.nextInt();
        }

        int[][] arr = new int[2][secondLength + 1];

        for (int i = 1; i <= firstLength; i++) {
            for (int j = 1; j <= secondLength; j++) {
                if (subsequenceOne[i] == secondSequence[j]) {
                    arr[i % 2][j] = 1 + arr[(i - 1) % 2][j - 1];
                } else {
                    arr[i % 2][j] = Integer.max(arr[(i - 1) % 2][j], arr[i % 2][j - 1]);
                }
            }
        }
        System.out.println(arr[firstLength % 2][secondLength]);
    }


}
