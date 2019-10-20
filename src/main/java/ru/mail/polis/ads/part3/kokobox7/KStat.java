package ru.mail.polis.ads.part3.kokobox7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class KStat {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int k = 0;

            k = Integer.parseInt(in.readLine());

            String input =  in.readLine();
            String[] nums = input.split(" ");

            BigInteger[] array = new BigInteger[nums.length];

            for (int i = 0; i < nums.length; i++) {
                array[i] = new BigInteger(nums[i]);
            }

            Arrays.sort(array);

            System.out.println(array[array.length - k]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}