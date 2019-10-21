package ru.mail.polis.ads.dz3;

import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/5912457

public class DZ3_TrickySorting {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] mass = new int[len];
        for (int i = 0; i < len; i++) {
            int a = in.nextInt();
            mass[i] = a;
        }
        notFullRadixSort(mass);
        for (int i : mass) {
            System.out.print(i + " ");
        }
    }

    private static void notFullRadixSort(int[] mass) {
        int swap;
        for (int i = 0; i < mass.length; i++) {
            for (int j = i; j < mass.length; j++) {
                if (mass[i] % 10 > mass[j] % 10) {
                    swap = mass[j];
                    mass[j] = mass[i];
                    mass[i] = swap;
                }
            }
        }

        for (int i = 0; i < mass.length; i++) {
            for (int j = i + 1; j < mass.length; j++) {
                if (mass[i] % 10 == mass[j] % 10) {
                    int max = Math.max(mass[i], mass[j]);
                    int min = Math.min(mass[i], mass[j]);
                    mass[i] = min;
                    mass[j] = max;
                }
            }
        }
    }
}
//    private static int checkPos(int left, int right, int mod) {
//        int min = 0;
//        if (right == left)
//            return left;
//        if (left % mod == right % mod){
//            mod *= 10;
//            min = checkPos(left, right, mod);
//        } else if (left % mod > right % mod){
//            min = right;
//        } else {
//            min = left;
//        }
//        return min;
//    }
//}

//import java.util.*;
//public class DZ3_TrickySorting {
//    public static class MyFun implements Comparator<Integer> {
//        public int compare(Integer a, Integer b) {
//            if (a % 10 == b % 10) {
//                return a - b;
//            } else {
//                return a % 10 - b % 10;
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Scanner con = new Scanner(System.in);
//        int n = con.nextInt();
//        Integer m[] = new Integer[n];
//        for(int i = 0; i < n; i++) {
//            m[i] = con.nextInt();
//        }
//
//        Arrays.sort(m, new MyFun());
//
//        for(int i = 0; i < n; i++) {
//            System.out.print(m[i] + " ");
//        }
//        System.out.println();
//        con.close();
//    }
//}