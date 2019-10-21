package ru.mail.polis.ads.part3.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

//e-olymp problem 4827 "k-тая порядковая статистика"

public class Task4 {
    private Task4(){
    }
    private static int k;
    private static ArrayList<BigInteger> list = new ArrayList<>();

    private static BigInteger findKStatistic() {
        int left = 0;
        int right = list.size() - 1;
        while (true) {

            int mid = partition(left, right);
            if (mid == k) {
                return list.get(k);
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int partition(int left, int right) {
        int i = left;
        int j = right;
        BigInteger p = list.get(left);
        while (i <= j) {
            while (list.get(i).compareTo(p) > 0)
                ++i;
            while (list.get(j).compareTo(p) < 0)
                --j;
            if (i >= j) {
                break;
            }
            BigInteger tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
        return j;
    }

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            k = Integer.parseInt(in.readLine()) - 1;
        String[] tmp = in.readLine().split(" ");
        for (String s : tmp) {
            list.add(new BigInteger(s));
        }
        PrintWriter output = new PrintWriter(new BufferedOutputStream(System.out));
        output.print(findKStatistic());
        output.close();
        in.close();
    }

    public static void main(final String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

