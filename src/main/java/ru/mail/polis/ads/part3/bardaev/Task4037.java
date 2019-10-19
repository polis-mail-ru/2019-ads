package ru.mail.polis.ads.part3.bardaev;

import java.util.Arrays;
import java.util.Scanner;

public class Task4037 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Bot[] bot = new Bot[n];

        for (int i = 0; i < n; i++) {
            bot[i] = new Bot(scan.nextInt(), scan.nextInt());
        }

        sortArray(0, bot.length, bot);

        for (int i = 0; i < bot.length; i++) {
            System.out.println(bot[i].getNums());
        }
    }

    public static void sortArray(int start, int end, Bot[] arr) {
        if (end - start < 2) return;

        int mid = (start + end) / 2;

        sortArray(start, mid, arr);
        sortArray(mid, end, arr);
        mergeArray(start, mid, end, arr);
    }

    public static void mergeArray(int start, int mid, int end, Bot[] arr) {
        Bot[] left = null, right = null;
        left = Arrays.copyOfRange(arr, start, mid);
        right = Arrays.copyOfRange(arr, mid, end);
        int posLeft = 0;
        int posRight = 0;

        for (int i = start; i < end; i++) {
            if (posLeft == left.length) {
                arr[i] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                arr[i] = left[posLeft];
                posLeft++;
            } else if (left[posLeft].n1 <= right[posRight].n1) {
                arr[i] = left[posLeft];
                posLeft++;
            } else {
                arr[i] = right[posRight];
                posRight++;
            }
        }
    }

    private static class Bot {
        int n1;
        int n2;

        Bot(int a, int b) {
            this.n1 = a;
            this.n2 = b;
        }

        public String getNums() {
            return n1 + " " + n2;
        }
    }
}
