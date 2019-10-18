package ru.mail.polis.ads.part3.nik27090;

import java.util.Scanner;

//Задача: "Хитрая сортировка"
//Решение: https://www.e-olymp.com/ru/submissions/5892987

public class TrickySorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr=new int[n+1];
        for (int i = 1; i <=n ; i++) {
            arr[i]=sc.nextInt();
        }

        arr[0]= Integer.MIN_VALUE;
        int j;
        int tmp;
        for (int i = 1; i <= n ; i++) {
            j=i;
            while (j>0 && arr[j]%10<=arr[j-1]%10){
                if (arr[j]%10!=arr[j-1]%10){
                    tmp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=tmp;
                    j=j-1;
                }else if(arr[j]<arr[j-1]){
                    tmp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=tmp;
                    j=j-1;
                } else{
                    j=j-1;
                }
            }

        }

        for (int i = 1; i <=n ; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
