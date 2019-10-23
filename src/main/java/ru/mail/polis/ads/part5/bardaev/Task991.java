package ru.mail.polis.ads.part5.bardaev;

import java.util.Scanner;

public class Task991 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String test = in.next();
        char[] p;
        char[] w;
        if(test.contains("*")){
            p=test.toCharArray();
            w=in.next().toCharArray();
        } else{
            p=in.next().toCharArray();;
            w=test.toCharArray();
        }
        int n= w.length;
        int m = p.length;
        boolean [][] d=new boolean[n+1][m+1];
        d[0][0]=true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m ; j++) {
                if (w[i-1]==p[j-1] || p[j-1]=='?'){
                    d[i][j]=d[i-1][j-1];
                } else if (p[j-1]=='*'){
                    d[i][j]=d[i-1][j] || d[i-1][j-1]||d[i][j-1];
                } else{
                    d[i][j]=false;
                }
            }
        }
        System.out.print(d[n][m]? "YES":"NO");
    }
}