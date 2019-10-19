package ru.mail.polis.ads.part3.bardaev;

import java.util.Scanner;

public class Task3738 {
    private Task3738() {}

    private static void solve() {
        Scanner in = new Scanner(System.in);
        int k = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        in.close();

        String[] arrS = s.trim().split(" ");
        int[] arr = new int[arrS.length];
        for (int i = 0; i < arrS.length; i++) {
            arr[i] = Integer.parseInt(arrS[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.print(arr[arr.length-k]);
    }

    public static void main(final String[] arg) {
            solve();

    }
}
