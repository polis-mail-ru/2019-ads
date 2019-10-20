package main.java.ru.mail.polis.ads.part3.Eretic431;

import java.util.Random;

public class Task4 {
    public static void main(String[] args) {

    }

    public static long kth(long[] array, int l, int r, int k) {
        int i = l;
        int j = r;

        long x = array[l + random.nextInt(r - l + 1)];

        while (i <= j) {
            while (array[i] < x) {
                i++;
            }
            while (array[j] > x) {
                j--;
            }
            if (i <= j) {
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
            if (l <= k && k <= j) {
                return kth(l, j, k);
            }
            if (i <= k && k <= r) {
                return kth(i, r, k);
            }
        }
        return array[k];
    }
}
