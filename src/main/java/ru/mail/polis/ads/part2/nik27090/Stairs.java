package ru.mail.polis.ads.part2.nik27090;

import java.util.Scanner;

//Задача: "Лесенка"
//Решение: https://www.e-olymp.com/ru/submissions/5841720

public class Stairs {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+2];
        for (int i = 1; i < n+1; i++) {
            dp[i]=sc.nextInt();
        }
        int k= sc.nextInt();

        for (int i = 1; i<n+2 ; i++) {
            int max = Integer.MIN_VALUE;
            for (int j=1; j<=k && j<=i; j++) {
                if (dp[i-j]>max){
                    max = dp[i-j];
                }
            }
            dp[i]=dp[i]+max;
        }
        System.out.println(dp[n+1]);
    }
}
