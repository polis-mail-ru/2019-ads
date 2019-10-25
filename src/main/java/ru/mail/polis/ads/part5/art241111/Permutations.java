package ru.mail.polis.ads.part5.art241111;

import java.io.PrintWriter;
import java.util.Scanner;

public class Permutations {

    private static boolean[] a = new boolean[20];
    private static int[] res = new int[20];
    private static StringBuilder outStr = new StringBuilder();

    private static void rec(int t, int n){

        if ( t == n + 1){
            for (int i = 1; i <= n; i++) outStr.append(res[i]).append(" ");
            outStr.append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!a[i]){
                    a[i] = true;
                    res[t] = i;
                    rec(t+1,n);
                    a[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        rec(1,in.nextInt());
        out.print(outStr);
        out.flush();
    }
}