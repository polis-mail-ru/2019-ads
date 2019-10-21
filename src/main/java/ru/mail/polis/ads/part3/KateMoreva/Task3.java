package ru.mail.polis.ads.part3.KateMoreva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//e-olymp problem 4741 "Сортировка пузырьком - 2"

public class Task3 {
    private Task3(){
    }

    private static void solve() throws IOException{
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(in.readLine());
        int[] mas =new int [n];
        String[] numbers = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            mas[i] = Integer.parseInt(numbers[i]);
        }
        int count=0;
         for(int i =0; i<n-1; i++){
            for(int j=0; j<n-1; j++){
                if (mas[j] > mas[j+1]){
                    int tmp = mas[j];
                    mas[j]=mas[j+1];
                    mas[j+1]=tmp;
                    count++;
                }

            }
        }
        System.out.println(count);

    }

    public static void main(String[] args){
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
