package ru.mail.polis.ads.part5.nik27090;

import java.util.Scanner;

//задача: Наибольшая последовательнократная подпоследовательность
//Решение: https://www.e-olymp.com/ru/submissions/5924840

public class LargestSequentialSubsequent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n ; i++) {
            a[i]= sc.nextInt();
        }
        int result=1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int max=0;
            for (int j = 0; j <i ; j++) {
                if (a[j]!=0 && a[i]%a[j]==0 && dp[j]>max){
                max = dp[j];
                }
            }
            dp[i]=max+1;
            if (dp[i]>result){
                result=dp[i];
            }
        }
        System.out.println(result);
    }
}
