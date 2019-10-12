package ru.mail.polis.ads.part2.nik27090;

import java.util.Scanner;

//Задача: "Мышка и зернышки"
//Решение: https://www.e-olymp.com/ru/submissions/5841254

public class Mouse {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] dp = new int[m+1][n+1];

        for (int i=m; i>=0 ;i--){
            for(int j=0; j<=n;j++){
                if (i==0 || j==0){
                    dp[i][j]=-1;
                } else{dp[i][j]=sc.nextInt();}
            }
        }
        dp[0][1]=0;

        for (int i=1;i<=m;i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + dp[i][j];
            }
        }

        int i = m;
        int j = n;
        String res="";
        while(i+j>2){
            if (dp[i-1][j]>dp[i][j-1]){
                i--;
                res=res+"F";
            }
            else {
                j--;
                res=res+"R";
            }
        }

        StringBuffer bf = new StringBuffer(res);
        System.out.println(bf.reverse());
    }
}