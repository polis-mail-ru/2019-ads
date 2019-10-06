package ru.mail.polis.ads.part2.kuzo_liza;

import java.util.Scanner;

public class MouseAndSeed {

    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] X = new long[100][100];
        String step = "";

        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j < m; j++){
                X[i][j] = in.nextLong();
            }
        }

        for(int i = 1; i < n; i++) {
            X[i][0] = X[i][0] + X[i - 1][0];
        }

        for(int j = 1; j < m; j++){
            X[0][j] = X[0][j] + X[0][j - 1];
        }

        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                X[i][j] = X[i][j] + Math.max(X[i - 1][j], X[i][j - 1]);
            }
        }

        int k = n - 1, t = m - 1;
        while (k > 0 || t > 0){
            if (k > 0 && t > 0){
                if (X[k - 1][t] > X[k][t - 1]){
                    step += "F";
                    k--;
                }
                else{
                    step += "R";
                    t--;
                }
            }
            else if (k == 0){
                step += "R";
                t--;
            }
            else if (t == 0){
                step += "F";
                k--;
            }
        }
        String reverse = new StringBuffer(step).reverse().toString();
        System.out.println(reverse);
    }
}
