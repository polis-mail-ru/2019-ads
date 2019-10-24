package ru.mail.polis.ads.valer1435.part5;
// https://www.e-olymp.com/ru/submissions/5923971
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem3968 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double c = in.nextDouble();
        double l = 0;
        double r = c;
        double x;
        double y;
        do {
            x = (r+l)/2;
            y = f(x);
            if (y > c){
                r = x;
            }else{
                l = x;
            }
        }while (Math.abs(y-c)>=1e-6);
        System.out.println(String.format("%f", x));
    }
    static double f(double x){
        return x*x+Math.sqrt(x);
    }
}
