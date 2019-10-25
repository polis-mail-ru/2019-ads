package ru.mail.polis.ads.part5.art241111;

import java.io.PrintWriter;
import java.util.Scanner;


public class Diploms {

    private static long binSearch(long w, long h, long n) {
        long min,max;
        if( w < h){
            min = h;
            max = h * n;
        } else{
            min = w;
            max = w * n;
        }

        while (min != max){
            long mid = (min + max) / 2;
            if(n <= (mid / w) * (mid / h)) max = mid;
            else min = mid + 1;
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long w = in.nextLong();
        long h = in.nextLong();
        long n = in.nextLong();

        out.print(binSearch(w,h,n));
        out.flush();
    }
}
