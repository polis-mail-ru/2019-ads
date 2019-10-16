package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SecondTask {

    private static int[] numbers;
    private static int amount;

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        final int n = Integer.parseInt(in.readLine());
        numbers = new int[100_000];
        amount = 0;
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            if ("0".equals(input[0])) {
                numbers[++amount] = Integer.parseInt(input[1]);
                swim(amount);
                continue;
            }
            out.println(numbers[1]);
            numbers[1] = numbers[amount--];
            sink(1);
        }
        out.close();
        in.close();
    }

    private static void swim(int k) {
        while (k > 1 && numbers[k] > numbers[k / 2]) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private static void sink(int k) {
        while (2 * k <= amount) {
            int j = 2 * k;

            if (j < amount && numbers[j] < numbers[j + 1]) {
                j++;
            }

            if (numbers[k] >= numbers[j]) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private static void swap(int first, int second) {
        int temp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = temp;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
