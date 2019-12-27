package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;

public class DZ4_Inversions {
    public static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        String[] nums = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }
        arr = logic(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
    }

    private static int[] logic(int[] arr) {
        if (arr.length < 2) return arr;
        int m = arr.length / 2;
        int[] arr1 = Arrays.copyOfRange(arr, 0, m);
        int[] arr2 = Arrays.copyOfRange(arr, m, arr.length);
        return merge(logic(arr1), logic(arr2));
    }

    public static int[] merge(int[] arr1, int arr2[]) {
        int[] arr = new int[arr1.length + arr2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < arr1.length + arr2.length; i++) {
            if (i1 == arr1.length) {
                arr[i] = arr2[i2++];
            } else if (i2 == arr2.length) {
                arr[i] = arr1[i1++];
            } else {
                if (arr1[i1] < arr2[i2]) {
                    arr[i] = arr1[i1++];
                } else {
                    arr[i] = arr2[i2++];
                }
            }
        }
        return arr;
    }
}
