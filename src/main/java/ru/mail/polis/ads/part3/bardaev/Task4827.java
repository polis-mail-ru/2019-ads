package ru.mail.polis.ads.part3.bardaev;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task4827 {
    private Task4827() {}
    static BigInteger[] arr;

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int k = Integer.parseInt(in.readLine());
        String s = in.readLine();
        String[] StrArr = s.split(" ");
        arr = new BigInteger[StrArr.length];
        for (int i = 0; i < arr.length; i++) arr[i] = new BigInteger(StrArr[i]);
        Arrays.sort(arr);
        out.println(arr[arr.length - k]);
        out.flush();
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {

        }
    }

}