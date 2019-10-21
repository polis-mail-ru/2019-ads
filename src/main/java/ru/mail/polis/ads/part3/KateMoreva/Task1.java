package ru.mail.polis.ads.part3.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//e-olymp problem 3738 "Простая сортировка"

public class Task1 {
    private Task1(){
    }
    private static int[] mas;

    private static void sort(final int start, final int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(start, mid);
        sort(mid + 1, end);

        final int n = end - start;
        int[] tmp = new int[n + 1];
        System.arraycopy(mas, start, tmp, 0, n + 1);

        int leftArray = 0;
        int rightArray = n / 2 + 1;

        for (int i = start; i <= end; i++) {

            if (leftArray > n / 2) {
               mas[i] = tmp[rightArray++];
            } else if (rightArray > n) {
                mas[i] = tmp[leftArray++];
            } else if (tmp[leftArray] > tmp[rightArray]) {
                mas[i] = tmp[rightArray++];
            } else {
                mas[i] = tmp[leftArray++];
            }
        }
    }

    private static void solve() throws IOException{
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(input.readLine());
        mas =new int [n];
        String[] numbers = input.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            mas[i] = Integer.parseInt(numbers[i]);
        }

        sort(0, n - 1);

        PrintWriter output = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < n; i++) {
            output.print(mas[i] + " ");
        }
        output.close();
        input.close();
    }
    public static void main(String[] args){
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


