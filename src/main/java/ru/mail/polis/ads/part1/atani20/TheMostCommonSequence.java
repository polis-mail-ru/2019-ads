package ru.mail.polis.ads.part1.atani20;
import java.util.Scanner;

public class TheMostCommonSequence {
    static int LCS(int[] X, int [] Y){
        int m = X.length;
        int n = Y.length;
        int[] lcsArr = new int[n];
        lcsArr[0] = 0;
        int temp1;
        int temp2 = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                temp1 = lcsArr[j];
                if(X[i] == Y[j])
                    lcsArr[j] = temp2 + 1;
                else if(j > 0 && lcsArr[j] < lcsArr[j-1])
                    lcsArr[j] = lcsArr[j - 1];
                temp2 = temp1;
            }
        }
        return lcsArr[n - 1];
    }
    public static void main(String[] args) {
        int n, m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[] X = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = in.nextInt();
        }
        m = in.nextInt();
        int[] Y = new int[m];
        for (int i = 0; i < m; i++) {
            Y[i] = in.nextInt();
        }
        int ans;
        if(X.length < Y.length)
            ans = LCS(Y, X);
        else
            ans = LCS(X, Y);
        System.out.println(ans);
    }
}