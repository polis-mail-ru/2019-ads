package ru.mail.polis.ads.part1.maksimshengeliia;

import java.io.*;
import java.util.StringTokenizer;

/*
 * https://www.e-olymp.com/ru/submissions/5792029
 * */
public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String string = in.next();
        String[][] dynamic = new String[string.length()][string.length()];
        for (int j = 0; j < string.length(); j++) {
            for (int i = j; i >= 0; i--) {
                String stringExtra = string.substring(i, j + 1);
                // если больше 4 - пытаемся упаковать,
                // если меньше - значит это максимально большая подстрока
                // ?(.) - 4 символа минимум
                if (stringExtra.length() > 4){
                    dynamic[i][j] = stringExtra;
                    int min = Integer.MAX_VALUE;
                    String res = stringExtra;
                    for (int k = i; k < j; k++) {
                        int previous = (dynamic[i][k] + dynamic[k+1][j]).length();
                        if (previous <= min){
                            min = previous;
                            res = dynamic[i][k] + dynamic[k+1][j];
                        }
                    }
                    for (int k = 1; k <= stringExtra.length() / 2 + 1; k++) {
                        if (stringExtra.length() % k != 0){
                            continue;
                        }
                        int c = 1;
                        boolean isPeriodic = false;
                        String curr = dynamic[i][i + k - 1];
                        for (int l = k; i + l + k - 1 <= j; l += k){
                            if (curr.equals(dynamic[i + l][i + l + k - 1])){
                                c += 1;
                            } else {
                                isPeriodic = true;
                                break;
                            }
                        }
                        if (!isPeriodic && c != 1) {
                            String minRes = c + "(" + curr + ")";
                            if (minRes.length() <= min) {
                                min = minRes.length();
                                res = minRes;
                            }
                        }
                    }
                    dynamic[i][j] = res;
                } else {
                    dynamic[i][j] = stringExtra;
                }
            }
        }
        System.out.println(dynamic[0][string.length() - 1]);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
