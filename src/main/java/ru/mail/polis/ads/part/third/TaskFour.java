package ru.mail.polis.ads.part.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5917378
 */
public class TaskFour {

    private static void solve(final PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine());
        String[] array = in.readLine().trim().split(" ");
        Arrays.sort(array, (i, j) -> {
            if (i.length() > j.length() ||
                    i.length() == j.length() &&
                            i.compareTo(j) > 0) {
                return 1;
            } else if (i.compareTo(j) == 0) {
                return 0;
            } else {
                return -1;
            }
        });
        out.print(array[array.length - k]);
    }

    public static void main(final String[] arg) throws IOException {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
