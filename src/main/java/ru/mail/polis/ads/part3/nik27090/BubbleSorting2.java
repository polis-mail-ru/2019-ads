package ru.mail.polis.ads.part3.nik27090;

import java.util.Scanner;

//Задача: "Сортировка пузырьком 2"
//Решение: https://www.e-olymp.com/ru/submissions/5893192

public class BubbleSorting2 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n =sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]=sc.nextInt();
        }

        int tmp;
        int result =0;
        for (int i = 0; i <n-1 ; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    result++;
                }
            }
        }
            System.out.println(result);
    }
}