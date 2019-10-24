package ru.mail.polis.ads.valer1435.part5;
// https://www.e-olymp.com/ru/submissions/5924687
import java.util.Scanner;

public class Problem3969 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long w = in.nextInt();
        long h = in.nextInt();
        if (w > h){
            long tmp = h;
            h = w;
            w = tmp;
        }
        long n = in.nextInt();
        long l = h;
        long r = h*n;
        long m = 0, v;

        while (r > l) {
            m = (r+l)/2;
            v = (m/w)*(m/h);
            if (v >= n) {
                r = m;
            } else {
                l = m+1;
            }
        }
        System.out.println(l);
    }

}
