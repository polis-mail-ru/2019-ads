package ru.mail.polis.ads.part1.ya111;

import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5833573
public final class problem15 {
    private problem15(){
    }

    public static void main(final String[] argc) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        long[][] X = new long[100][100];
        String ans = new String("");
        for (int i=n-1; i>=0; i--){
            for (int j=0; j<m; j++)X[i][j]=sc.nextLong();
        }
        for(int i=1;i<n;i++)
            X[i][0]=X[i][0]+X[i-1][0];

        for(int j=1;j<m;j++)
            X[0][j]=X[0][j]+X[0][j-1];

        for (int i=1; i<n; i++){
            for (int j=1; j<m; j++)
                X[i][j]=X[i][j]+Math.max(X[i - 1][j], X[i][j - 1]);
        }

        int k=n-1, t=m-1;

        while (k>0 || t>0){
            if (k>0 && t>0){
                if (X[k-1][t]>X[k][t-1]){
                    ans+="F";
                    k--;
                }
                else{
                    ans+="R";
                    t--;
                }
            }
            else if (k==0){
                ans+="R";
                t--;
            }
            else if (t==0){
                ans+="F";
                k--;
            }
        }
        String reverse = new StringBuffer(ans).reverse().toString();
        System.out.println(reverse);
    }
}
