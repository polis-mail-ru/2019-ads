package ru.mail.polis.ads.part2.Kungurov;

import java.io.PrintWriter;
import java.util.Scanner;
/**
 * created by kirill.kungurov on 15.09.2019
 * https://www.e-olymp.com/ru/submissions/5864328
 */
public final class SolveOfProblem15 {
    private static void solve(final Scanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        int[][] arr = new int[m + 1][n + 1];
        String[][] path = new String[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }
        for (int i=0;i<m+1;i++){
            for (int j=0;j<n+1;j++){
                path[i][j]="";
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (!(i==m-1 && j==0)){
                    if (i==m-1){
                        arr[i][j] = arr[i][j-1];
                        path[i][j] = path[i][j-1] + "R";
                    } else{
                        if (j==0){
                            arr[i][j] = arr[i+1][j];
                            path[i][j] = path[i+1][j] + "F";
                        }else{
                            if (arr[i][j-1]>=arr[i + 1][j]){
                                arr[i][j] = arr[i][j-1];
                                path[i][j] = path[i][j-1] + "R";
                            } else {
                                arr[i][j] = arr[i+1][j];
                                path[i][j] = path[i+1][j] + "F";
                            }

                        }
                    }
                }
                arr[i][j]+=field[i][j];
            }
        }
        out.println(path[0][n-1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
