package ru.mail.polis.ads.part4.kuzo_liza;

import java.util.Scanner;

public class CowsInStall {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] coordinates = new int[n];

        for (int i = 0; i < n; i++) {
            coordinates[i] = scanner.nextInt();
        }

        System.out.println(distance(coordinates, n, k));
    }

    public static int distance(int[] coordinates, int n, int k){
        if (coordinates.length == 3 || k == 2) {
            return coordinates[n - 1] - coordinates[0];
        }

        int left = 0;
        int right = coordinates[n - 1] - coordinates[0];
        int middle;

        while (left != right){
            middle = (left + right) / 2;
            int cowCounter = 1;
            int stallNumber = 0;

            for(int i = 1; i < n; i++) {
                if (coordinates[i] - coordinates[stallNumber] >= middle) {
                    stallNumber = i;
                    cowCounter++;
                }
            }
            if(cowCounter >= k){
                left = middle + 1;
            }
            else {
                right = middle;
            }
        }

        return left - 1;
    }
}