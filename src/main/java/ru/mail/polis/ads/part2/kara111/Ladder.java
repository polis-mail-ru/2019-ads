package ru.mail.polis.ads.part2.kara111;

import java.util.Scanner;

public class Ladder {
    public static void main( String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MIN = Integer.MIN_VALUE;
        //int all_max = 0;
        int n = scanner.nextInt();
        int[] arr = new int[n+2];
        arr[0] = arr[arr.length-1] = 0;
        for (int i = 1; i <n+1; i++){
            arr[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        for (int i = 1; i < n + 2; i++){
            int max = MIN;
            for(int j = 0; j < k; ) {
                j++;
                if (i - j >= 0) {
                    if (arr[i - j] > max)
                        max = arr[i - j];
                }
            }
            arr[i] +=max;
            //System.out.println(max);

            //System.out.println(all_max);
        }
        System.out.println(arr[arr.length - 1]);
    }
}


