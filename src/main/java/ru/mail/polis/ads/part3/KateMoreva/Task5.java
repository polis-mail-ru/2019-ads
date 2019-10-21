package ru.mail.polis.ads.part3.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//e-olymp problem 4037 "Сортировка слиянием"

public class Task5 {
    private Task5(){
    }
    private static int[][] mas;

    private static void sort(final int start, final int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(start, mid);
        sort(mid + 1, end);

        final int n = end - start;
        int[][] tmp = new int[n + 1][2];
        if (n + 1 >= 0) System.arraycopy(mas, start, tmp, 0, n + 1);

        int leftArray = 0;
        int rightArray = n / 2 + 1;

        for (int i = start; i <= end; i++) {

            if (leftArray > n / 2) {
                mas[i] = tmp[rightArray++];
            } else if (rightArray > n) {
                mas[i] = tmp[leftArray++];
            } else if (tmp[leftArray][0] > tmp[rightArray][0]) {
                mas[i] = tmp[rightArray++];
            } else {
                mas[i] = tmp[leftArray++];
            }
        }
    }

    private static void solve() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(input.readLine());
        mas =new int [n][2];
        for(int i = 0; i < n; i++){
            String[] tmp  = input.readLine().split(" ");
            mas[i][0] = Integer.parseInt(tmp[0]);
            mas[i][1] = Integer.parseInt(tmp[1]);
        }
        sort(0, n - 1);
        PrintWriter output = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i =0; i < n; i++) {
            output.println(mas[i][0] + " " + mas[i][1]);
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
