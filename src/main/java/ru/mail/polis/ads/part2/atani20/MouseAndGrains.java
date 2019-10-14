package ru.mail.polis.ads.part2.atani20;

import java.util.Scanner;
public class MouseAndGrains {
    static private int[][] FillPathMatrix(int[][] grain, int n, int m){
        int[][] path = new int[n][m];
        path[n-1][0] = grain[n-1][0];
        for(int i = 1; i < m; i++){
            path[n - 1][i] = path[n-1][i-1]+ grain[n - 1][i];
        }
        for(int i = n - 2; i >= 0; i--){
            path[i][0] = path[i+1][0] + grain[i][0];
        }
        for(int i = n - 2; i >= 0; i--){
            for(int j = 1; j < m; j++){
                path[i][j] = (path[i+1][j] > path[i][j-1] ? path[i+1][j]:path[i][j-1]) + grain[i][j];
            }
        }
        return path;
    }

    static private String RestorePath(int[][] path, int n, int m){
        StringBuilder answer = new StringBuilder();
        int  i = 0;
        int j = m - 1;
        for(int k = 0; k < m + n - 2; k++){
            if(i == n - 1){
                answer.append('R');
                j--;
            }
            else if(j == 0){
                answer.append('F');
                i++;
            }
            else {
                if(path[i+1][j] > path[i][j-1]){
                    answer.append('F');
                    i++;
                }
                else{
                    answer.append('R');
                    j--;
                }
            }

        }
        answer.reverse();
        return answer.toString();
    }

    public static void main(String[] args){
        int n, m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[][] grain = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grain[i][j] = scanner.nextInt();
            }
        }
        int[][] path = FillPathMatrix(grain, n, m);
        System.out.println(RestorePath(path, n, m));
    }
}