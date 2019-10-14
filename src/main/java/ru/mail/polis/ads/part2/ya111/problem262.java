package ru.mail.polis.ads.part2.ya111;

import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5833486
public final class problem262 {
    private problem262(){
    }

    public static void main(final String[] argc) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] Array = new int [n+2];
        for (int i = 1; i <= n; ++i)
            Array[i]= sc.nextInt();
        Array[n+1]=0;
        int [] d = new int [n+2];
        int k = sc.nextInt();
        d[0]=0;

        for (int i = 1; i < n+2; ++i){
            d[i]=d[i-1]+Array[i];
            for (int j = i - 2; j>= i - k && j>=0; j--) {
                int s = d[j] + Array[i];
                if (s > d[i]) {
                    d[i]=s;
                }
            }
        }
        System.out.println(d[n+1]);
    }
}
