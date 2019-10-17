package ru.mail.polis.ads.part3.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine());

        String[] numbers = bf.readLine().split(" ");
        int length = numbers.length;
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            array[i] = new BigInteger(numbers[i]);
        }

        Arrays.sort(array);

        System.out.println(array[length - k]);
    }
}