package ru.mail.polis.ads.valer1435.part5;
// https://www.e-olymp.com/ru/submissions/5925316
import java.util.Scanner;

public class Problem991 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();

        char[] w;
        char[] p;
        if (s1.contains("*") || s1.contains("?")){
            w = s2.toCharArray();
            p = s1.toCharArray();
        }else{
            p = s2.toCharArray();
            w = s1.toCharArray();
        }

        int n = w.length;
        int m = p.length;
        boolean[][] d = new boolean[n+1][m+1];
        d[0][0] = true;
        for (int i = 1; i <= n; i++){
            for (int j=1; j <= m; j++ ){
                if (w[i-1] == p[j-1] || p[j-1]=='?'){
                    d[i][j] = d[i-1][j-1];
                }else if (p[j-1] == '*'){
                    d[i][j] = d[i-1][j] || d[i-1][j-1]|| d[i][j-1];
                }else{
                    d[i][j] = false;
                }
            }
        }if (d[n][m]){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}
