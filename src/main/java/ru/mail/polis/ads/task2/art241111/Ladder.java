package ru.mail.polis.ads.task2.art241111;


import java.io.PrintWriter;
import java.util.Scanner;

/**
        Link to result: https://www.e-olymp.com/ru/submissions/5780412
 **/

public class Ladder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.nextLine().trim ()) + 2;
        int[] a = new int[n * 2];

        int i = 1;
        a[0] = 0;

        for (String str: in.nextLine().split(" ")) {
            a[i] = Integer.parseInt(str.trim ());
            i++;
        }

        int k = Integer.parseInt(in.nextLine().trim ());

        for (i = 1; i <= n+1 ; i++) {
            if(i - 1 >= 0){
                int t = a[i-1];

                for (int j = 2; j <= k ; j++) {
                    if(i-j >= 0) t = Math.max(t,a[i - j]);
                }
                a[i] = a[i] + t;
            }
        }

        out.print(a[n+1]);
        out.flush();
    }
}