package ru.mail.polis.ads.part3.kokobox7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class KStat {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int k = Integer.parseInt(in.readLine());

            String input = in.readLine();
            String[] nums = input.split(" ");

            BigInteger[] array = new BigInteger[nums.length];

            for (int i = 0; i < nums.length; i++) {
                array[i] = new BigInteger(nums[i]);
            }

            System.out.println(findKStatistic(array, k));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BigInteger findKStatistic(BigInteger[] array, int k) {
        k = k - 1;
        int left = 0;
        int right = array.length - 1;
        while (true) {
            int pivot = partition(array, left, right);
            if (k == pivot) {
                return array[pivot];
            } else if (k < pivot) {
                right = pivot;
            } else {
                left = pivot + 1;
            }
        }
    }

    private static int partition(BigInteger[] array, int left, int right) {
        BigInteger pivot = array[left];
        int i = left;
        int j = right;
        while (true) {
            while (array[i].compareTo(pivot) > 0) {
                i++;
            }
            while (array[j].compareTo(pivot) < 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        return j;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}