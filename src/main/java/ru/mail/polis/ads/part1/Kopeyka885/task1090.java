package ru.mail.polis.ads.part1.Kopeyka885;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();
        String[][] packed = new String[n][n];
        for (int len = 1; len <= n; len++){
            for (int left = 0; left + len - 1 < n; left++){
                int right = left + len -1;
                String min = s.substring(left,right+1);
                if (len > 4){
                    for (int right1 = left; right1 < right; right1++ ){
                        int left2 = right1+1;
                        String t = packed[left][right1] + packed[left2][right];
                        if (t.length() < min.length()){
                            min = t;
                        }
                    }
                    for (int p = 1; p < len; p++){
                        if (len % p == 0){
                            boolean isPeriodic = true;
                            for (int i = left + p; i <=right; i++){
                                if (s.charAt(i) != s.charAt(i-p)){
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic){
                                String t = len/p + "(" + packed[left][left+p-1]+")";
                                if (t.length() < min.length()){
                                    min = t;
                                }
                            }
                        }
                    }
                }
                packed[left][right] = min;
            }
        }
        System.out.printf("%s", packed[0][n-1]);
    }
}