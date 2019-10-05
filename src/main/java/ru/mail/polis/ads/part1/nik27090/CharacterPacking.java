package ru.mail.polis.ads.part1.nik27090;

import java.util.Scanner;

/*
Задача: Упаковка символов
Решение: https://www.e-olymp.com/ru/submissions/5784499
 */

public class CharacterPacking {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int n = input.length();
        String[][] dp = new String[n][n];

        for(int len = 1; len<=n; len++){
            for(int left = 0; left+len-1<n;left++){
                int right = left+len-1;
                String min = input.substring(left,left+len);
                if (len>4){
                    for (int right1 = left; right1<right;right1++){
                        int left2 =right1+1;
                        String t = dp[left][right1]+dp[left2][right];
                        if (t.length()<min.length()){
                            min = t;
                        }
                    }
                    for (int p =1; p< len;p++){
                        if (len %p==0){
                            boolean isPeriodic = true;
                            for (int i=left+p; i<=right;i++){
                                if (input.charAt(i) != input.charAt(i-p)){
                                    isPeriodic=false;
                                    break;
                                }
                            }
                            if (isPeriodic){
                                String t = String.valueOf(len/p) + "(" + dp[left][left+p-1] + ")";
                                if (t.length() < min.length()){
                                    min=t;
                                }
                            }
                        }
                    }
                }
                dp[left][right]=min;
            }
        }
        System.out.println(dp[0][n-1]);
    }
}
