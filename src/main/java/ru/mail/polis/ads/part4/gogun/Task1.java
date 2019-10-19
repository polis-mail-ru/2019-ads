package ru.mail.polis.ads.part4.gogun;

import java.io.*;

public class Task1 {
    static long[] array;

    private static String check(int n) {
        for (int i = 1; i <= 2/n+3; ++i) {
            if (array[i] <= array[2*i] && array[i] <= array[2*i+1]) {
                continue;
            } else {
                return "NO";
            }
        }
        return "YES";
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int n = Integer.parseInt(input.readLine());
            n+=1;
            array = new long[n];
            array[0] = -1;

            int i = 1;
            for (String a: input.readLine().split(" ")) {
                array[i] = Long.parseLong(a);
                ++i;
            }

            output.println(check(n));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
