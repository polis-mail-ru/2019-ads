package ru.mail.polis.ads.part2.atani20;

import java.util.Scanner;

public class Stairs {
    public static void main(String[] args){
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] arr = new int[n + 2];
        arr[0] = 0;
        arr[n - 1] = 0;
        for(int i = 1; i < n + 1; i++){
            arr[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int[] path = new int[n+2];
        path[0] = 0;
        path[1] = arr[1];
        for(int i = 1; i < n + 2; i++){
            int max = path[i-1];
            int j = (i - k) < 0 ? 0: (i - k);
            for(; j < i; j++) {
                if (max < path[j]){
                    max = path[j];
                }
            }
            path[i] = arr[i] + max;
        }
        System.out.print(path[n+1]);
    }
}