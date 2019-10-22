package ru.mail.polis.ads.part3.kasimova;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = n; i > 1; i--){
            for (int j = 0; j < i-1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count ++;
                }
            }
        }
        for (int i = 0; i < n; i ++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(count);
    }



}
