package ru.mail.polis.ads.part4.nik27090;

import java.util.Scanner;

//Задача: "Куча ли?"
//Решение: https://www.e-olymp.com/ru/submissions/5953279

public class IsItHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] heap = new long[n+1];
        for (int i = 1; i <= n ; i++) {
            heap[i]=sc.nextLong();
        }

        int i;
        for (i  = 1; i <=n/2 ; i++) {
            if (2*i<=n && heap[i]>heap[i*2])
                break;
            if (2*i+1<=n && (heap[i]>heap[2*i+1]))
                break;
        }

        if (i<=n/2){
            System.out.println("NO");
        }   else {
            System.out.println("YES");
        }
    }
}
