package ru.mail.polis.ads.valer1435.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// https://www.e-olymp.com/ru/submissions/5787767#source
public class Problem15 {
    public static void main(String[] args){
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[n][m];
        for (int i=0; i < n; i++){
            for (int j=0; j < m; j++){
                arr[i][j] = in.nextInt();
            }
        }

        int[][] d = new int[n][m];
        Character[][] d_way = new Character[n][m];

        for (int j=0; j < m; j++){
            for (int i=n-1; i >= 0; i--){
                if (i == n-1 && j == 0){
                    d[i][j] = arr[n-1][0];
                    continue;
                }
                int right_way = -1;
                int up_way = -1;
                if (j != 0){
                    right_way = d[i][j-1]+arr[i][j];
                }
                if (i != n-1){
                    up_way = d[i+1][j]+arr[i][j];
                }
                if (right_way > up_way){
                    d[i][j] = right_way;
                    d_way[i][j] = 'R';
                }else{
                    d[i][j] = up_way;
                    d_way[i][j] = 'F';
                }

            }
        }
        restore(d_way, 0, m-1, "");
    }
    private static void restore(Character[][] d_way, int i, int j, String res){
        if (i == d_way.length-1 && j == 0){
            res = new StringBuilder(res).reverse().toString();
            System.out.println(res);
            return;
        }
        res +=d_way[i][j];
        if (d_way[i][j] == 'R'){
            restore(d_way, i, j-1, res);
        }else if (d_way[i][j] == 'F'){
            restore(d_way, i+1, j, res);
        }
    }
    public static void printArr(Character[][] arr){
        for (int l = 0; l<arr.length; l++) {
            System.out.println(Arrays.toString(arr[l]));
        }
        System.out.println(" ");
    }
}




class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }
}