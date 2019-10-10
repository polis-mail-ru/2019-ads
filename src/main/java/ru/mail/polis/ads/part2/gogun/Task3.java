package ru.mail.polis.ads.part2.gogun;

import java.io.*;
import java.util.*;
import java.lang.*;

class Task3 {
    static int index;
    static char[] inputString;
    static String[] outputString;

    private static void get_levels(int depth) {
        outputString[depth] += inputString[index];
        index--;
        if (Character.isUpperCase(inputString[index + 1])) {
            get_levels(depth + 1);
            get_levels(depth + 1);
        }
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int n = Integer.parseInt(input.readLine());
            String str;

            for (int k = 0; k < n; k++) {
                str = input.readLine();
                inputString = str.toCharArray();
                outputString = new String[inputString.length];
                Arrays.fill(outputString, "");
                index = inputString.length - 1;
                get_levels(0);
                StringBuilder sb = new StringBuilder(inputString.length);
                for (int j = inputString.length - 1; j >= 0; j--)
                    sb.append(outputString[j]);
                output.println(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}