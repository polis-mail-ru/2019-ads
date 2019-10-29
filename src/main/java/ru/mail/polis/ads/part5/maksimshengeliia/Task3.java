package ru.mail.polis.ads.part5.maksimshengeliia;

import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5972054
* */
public class Task3 {

    public static void main(String[] args) {
        int[] dynamic;
        Scanner sc = new Scanner(System.in);
        int[] a;
        int num = sc.nextInt();
        a = new int[num];
        dynamic = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = sc.nextInt();
        }
        dynamic[0] = 1;
        int ans = 1;
        for (int i = 0; i < num; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && dynamic[j] > max)  {
                    max = dynamic[j];
                }
                dynamic[i] = max + 1;
                if (dynamic[i] > ans) ans = dynamic[i];
            }
        }
        System.out.println(ans);
    }
}


