package ru.mail.polis.ads.part2.gogun;

import java.util.Scanner;

public class Task2 {

    private static void createArray(int[] a, int n, Scanner input) {
        for (int i = 1; i <= n; ++i) {
            a[i] = input.nextInt();
        }
    }

    private static void solve(int n, int[] arrayOfDynamic, int[] costsArray, int k){
        for (int i = 1; i < n + 2; ++i) {

            int max = Integer.MIN_VALUE;
            for (int j = i - 1; j >= i - k; --j) {
                if (j < 0) {
                    break;
                }
                if (arrayOfDynamic[j] > max) {
                    max = arrayOfDynamic[j];
                }
            }

            arrayOfDynamic[i] = max + costsArray[i];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n =  input.nextInt();
        int[] costsArray = new int[n+2];
        costsArray[0] = 0;
        costsArray[n+1] = 0;

        createArray(costsArray, n, input);

        int k = input.nextInt();
        int[] arrayOfDynamic = new int[n+2];
        arrayOfDynamic[0] = 0;

        solve(n, arrayOfDynamic, costsArray, k);

        System.out.println(arrayOfDynamic[n+1]);
    }
}
