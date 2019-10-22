package ru.mail.polis.ads.part5.nik27090;

import java.util.Scanner;

//Задача: Дипломы
//Решение: https://www.e-olymp.com/ru/submissions/5924397

public class Diplomas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long w=sc.nextInt();
        long h=sc.nextInt();
        long  n=sc.nextInt();
        long left = Math.max(w,h);
        long right= n*left;
        long m;
        long v;
        while (left<right){
            m=(left+right)/2;
            v = (m/w)*(m/h);
            if (v>=n){
                right=m;
            } else {
                left=m+1;
            }
        }
        System.out.println(right);
    }
}
