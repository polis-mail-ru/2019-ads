package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*Определим правильное скобочное выражение следующим образом:

    Пустое выражение - правильное.
    Если выражение S правильное, то (S) и [S] также правильные.
    Если выражения A и B правильные, то и выражение AB правильное.

Дана последовательность скобок (, ), [, и ].
Требуется найти самое короткое правильное выражение, в котором данная последовательность является подпоследовательностью,
то есть такое, из которого можно вычеркнуть некоторые символы (возможно, ноль) и получить исходную последовательность, не меняя порядок оставшихся.

Входные данные
В первой строке находятся символы (, ), [, и ] без пробелов. Исходная последовательность содержит не более 100 скобок.

Выходные данные
Вывести искомую последовательность скобок без пробелов.
* */

//https://www.e-olymp.com/ru/submissions/5773335

public final class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        if (input.isEmpty()) {
            out.println();
            return;
        }
        int n = input.length();
        int[][] d = new int[n][n];
        int[][] split = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    d[j][i] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (input.charAt(j) == '(' && input.charAt(i) == ')' ||
                        input.charAt(j) == '[' && input.charAt(i) == ']'){
                    min = d[j+1][i-1];
                }
                for (int k = j; k < i; k++) {
                    if (d[j][k] + d[k+1][i] < min){
                        min = d[j][k] + d[k+1][i];
                        splitMin = k;
                    }
                }
                d[j][i] = min;
                split[j][i] = splitMin;
            }
        }
        restore(0, n-1, input, d, split);
    }

    private static void restore(int i, int j,
                                String s, int[][] d, int[][] split){
        if (i == j){
            switch (s.charAt(i)){
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
            }
            return;
        }
        if (d[i][j] == 0){
            System.out.print(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1){
            System.out.print(s.charAt(i));
            restore(i+1, j-1, s, d, split);
            System.out.print(s.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, s, d, split);
        restore(k + 1, j, s, d, split);
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

