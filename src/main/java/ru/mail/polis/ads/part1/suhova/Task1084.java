package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1084 {
    private static int[][] len;

    private static void solve(final Task1084.FastScanner in, final PrintWriter out) {
        String str = in.next();
        len = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            Arrays.fill(len[i], -1);
        }
        out.println(seqCreator(0, str.length() - 1, str));
        out.flush();
    }

    private static String seqCreator(int l, int r, String str) {
        if (l == r + 1) return "";
        switch (str.charAt(l)) {
            case ']':
                return "[]" + seqCreator(l + 1, r, str);
            case ')':
                return "()" + seqCreator(l + 1, r, str);
        }
        switch (str.charAt(r)) {
            case '[':
                return seqCreator(l, r - 1, str) + "[]";
            case '(':
                return seqCreator(l, r - 1, str) + "()";
        }
        int ind = partitionIndex(l, r, str);
        if (ind == -10) return str.charAt(l) + seqCreator(l + 1, r - 1, str) + str.charAt(r);
        else return seqCreator(l, ind, str) + seqCreator(ind + 1, r, str);
    }

    private static int partitionIndex(int l, int r, String str) {
        int minIndex = l;
        int min = 2 + minLen(l + 1, r, str);
        int sum = 2 + minLen(l, r - 1, str);
        if (min > sum) {
            min = sum;
            minIndex = r-1;
        }
        for (int i = l + 1; i < r-1; i++) {
            sum = minLen(l, i, str) + minLen(i + 1, r, str);
            if (min > sum) {
                min = sum;
                minIndex = i;
            }
        }
        len[l][r] = min;
        if (str.charAt(l) == '(' && str.charAt(r) == ')' || str.charAt(l) == '[' && str.charAt(r) == ']') {
            int x = minLen(l + 1, r - 1, str) + 2;
            if (x < len[l][r]) {
                len[l][r] = x;
                minIndex = -10;
            }
        }
        return minIndex;
    }

    private static int minLen(int l, int r, String str) {
        if (l == r) {
            len[l][r] = 2;
            return 2;
        }
        if (l > r) {
            len[l][r] = 0;
            return 0;
        }
        if (len[l][r] == -1) {
            if (str.charAt(l) == ')' || str.charAt(l) == ']') {
                len[l][r] = 2 + minLen(l + 1, r, str);
            } else if (str.charAt(r) == '(' || str.charAt(r) == '[') {
                len[l][r] = 2 + minLen(l, r - 1, str);
            } else {
                int min = 2 + minLen(l + 1, r, str);
                int sum = 2 + minLen(l, r - 1, str);
                if (min > sum) min = sum;
                for (int i = l + 1; i < r; i++) {
                    sum = minLen(l, i, str) + minLen(i + 1, r, str);
                    if (min > sum) min = sum;
                }
                len[l][r] = min;
                if (str.charAt(l) == '(' && str.charAt(r) == ')' || str.charAt(l) == '[' && str.charAt(r) == ']') {
                    int x = minLen(l + 1, r - 1, str) + 2;
                    if (x < len[l][r]) len[l][r] = x;
                }
            }
        }
        return len[l][r];
    }

    public static void main(final String[] arg) {
        final Task1084.FastScanner in = new Task1084.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}