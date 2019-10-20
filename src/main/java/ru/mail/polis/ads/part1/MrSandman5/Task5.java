package part1;

import java.io.*;
import java.util.StringTokenizer;

/*Наибольшая общая подпоследовательность

Даны две последовательности. Найдите длину их наибольшей общей подпоследовательности.
Подпоследовательность - это последовательность, полученная из другой последовательности удалением некоторых элементов без изменения порядка следования оставшихся элементов.

Входные данные
В первой строке задана длина n (1 ≤ n ≤ 1000) первой последовательности.
Во второй строке записаны члены первой последовательности - целые числа, не превосходящие по модулю 10000.
В третьей строке задана длина второй последовательности m (1 ≤ m ≤ 1000).
В четвертой строке записаны члены второй последовательности - целые числа, не превосходящие по модулю 10000.

Выходные данные
Вывести длину наибольшей общей подпоследовательности, или 0 если такой не существует.
* */

//https://www.e-olymp.com/ru/submissions/5773327

public final class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++){
            x[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] y = new int[m];
        for (int i = 0; i < m; i++){
            y[i] = in.nextInt();
        }
        int[][] L = new int[n+1][m+1];

        for (int i = 0; i <= n; i++)
        {
            for (int j=0; j <= m; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (x[i-1] == y[j-1])
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        out.println(L[n][m]);
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
