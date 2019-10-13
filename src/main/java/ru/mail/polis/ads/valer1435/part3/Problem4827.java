package ru.mail.polis.ads.valer1435.part3;

// https://www.e-olymp.com/ru/submissions/5851074

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4827 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int cnt = in.nextInt();
        in.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(in.nextLine());
        int len = tokenizer.countTokens();
        BigInteger[] arr = new BigInteger[len];
        for (int i = 0; i < len; i++){
            arr[i] = new BigInteger(tokenizer.nextToken());
        }
        cnt = arr.length-cnt;
        k_stat(arr, 0, arr.length-1, arr.length/2, cnt);
        out.print(arr[cnt]);
        out.flush();

    }

    public static void k_stat(BigInteger[] arr, int left, int right, int mid, int cnt){
        int l = left;
        int r = right;
        BigInteger basic = arr[mid];



        while (l <= r){
            while (arr[l].compareTo(basic) < 0){
                l++;
            }
            while (arr[r].compareTo(basic) > 0){
                r--;
            }
            if (l <= r){
                BigInteger b = arr[r];
                arr[r] = arr[l];
                arr[l] = b;
                r--;
                l++;
            }

        }
        if (cnt <= r){
            right = r;
            k_stat(arr, left, right, (right-left)/2+left,  cnt);
        }
        else if (cnt >= l){
            left = l;
            k_stat(arr, left, right, (right-left)/2+left,  cnt);
        }
        else{
            return;
        }
    }
}

/*
6
1 2 3 4 5 6
*/