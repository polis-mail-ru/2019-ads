package ru.mail.polis.ads.part5.KateMoreva;

import java.util.Scanner;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

//e-olymp problem 3968 "Квадратный корень"

public class Task1 {
    private Task1(){
    }
    private static  double f(double x){
        return x*x+sqrt(x);
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double c = input.nextDouble();
        double le =0;
        double r=c;
        double x,y;
                do {
                    x = (le + r) / 2;
                    y = f(x);
                    if(y>c){
                        r=x;
                    } else {
                        le=x;}
                }while (abs(y-c)>1e-6);
                System.out.println(String.format("%.6f",x));
    }

}
