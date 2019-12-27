package ru.mail.polis.ads;

import java.util.Scanner;

public class DZ4_SetCow {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n =sc.nextInt();
        int k=sc.nextInt();
        int[] arr = new int [n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        System.out.println(maxMinDist(arr,k));
    }

    public static int maxMinDist(int[] arr, int k){
        int n=arr.length;
        if (arr.length==3 || k==2)
            return arr[n-1]-arr[0];
        int left =0;
        int right = arr[n-1]-arr[0];
        int mid;
        int j; //номер стойла3 с коровой
        int g; //счетчик коров на выбранном расстоянии
        while (left!=right){
            mid=(left+right)/2;
            g=1;
            j=0;
            for (int i = 1; i <n ; i++) {
                if (arr[i]-arr[j]>=mid) {
                    j = i;
                    g++;
                }
            }
            if(g>=k){
                left=mid+1;
            } else{
                right=mid;
            }
        }
        return left-1;
    }
}