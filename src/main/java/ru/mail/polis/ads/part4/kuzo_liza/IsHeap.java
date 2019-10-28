package ru.mail.polis.ads.part4.kuzo_liza;

import java.util.Scanner;

public class IsHeap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] heap = new long[n + 1];

        for(int j = 1; j <= n; j++) {
            heap[j] = scanner.nextLong();
        }

        int i;
        for(i = 1; i <= n / 2; i++){
            if ((2 * i <= n) && (heap[i] >  heap[2 * i])) break;
            if ((2 * i + 1 <= n) && (heap[i] >  heap[2* i + 1])) break;
        }

        if (i <= n/2){
            System.out.println("NO");
        }   else {
            System.out.println("YES");
        }
    }

}
