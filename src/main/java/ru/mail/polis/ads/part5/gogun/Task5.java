package ru.mail.polis.ads.part5.gogun;

import java.io.*;
import java.util.*;

public class Task5 {
    static int[] array;
    static ArrayList<String> strArr = new ArrayList<>();

    private static void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void permute(int[] arr, int k){
        if (k == arr.length -1) {
            strArr.add(Arrays.toString(arr).replace(",", "").replace("[", "").replace("]", ""));
        }
        for(int i = k; i < arr.length; i++){
            swap(i, k);
            permute(arr, k+1);
            swap(k, i);
        }

    }

    public static void solve(BufferedReader in, PrintWriter out) {

        try {
            int n = Integer.parseInt(in.readLine());
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i + 1;
            }

            permute(array, 0);

            Collections.sort(strArr);
            for (int i = 0; i < strArr.size(); i++) {
                out.println(strArr.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
