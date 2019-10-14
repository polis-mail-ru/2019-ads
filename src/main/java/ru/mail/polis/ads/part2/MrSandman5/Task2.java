package part2;

/*На каждой из n + 2 ступенек лестницы записано целое число, причем на первой и на последней ступеньке записано число 0.
На первой ступеньке стоит человек, которому необходимо подняться на последнюю ступеньку.
За один шаг он может подниматься на любое число ступенек, не превосходящее k.

Подсчитаем сумму всех чисел, написанных на ступеньках, на которые наступил человек. Найдите наибольшее возможное значение этой суммы.

Входные данные
В первой строке записано число n (0 ≤ n ≤ 1000).
Во второй строке записано n целых чисел, не превосходящих по модулю 1000, разделенных пробелами - числа, записанные на ступеньках (за исключением первой и последней ступеньки, на которых записаны нули).
В третьей строке записана максимальная величина шага человека k (1 ≤ k ≤ n).

Выходные данные
Выведите максимально возможную сумму чисел, записанных на ступеньках, на которые наступил человек.
* */

//https://www.e-olymp.com/ru/submissions/5848296
import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairs = new int[1002];
        for (int i = 1; i <= n; i++) {
            stairs[i] = in.nextInt();
        }
        int step = in.nextInt();
        stairs[0] = stairs[n + 1] = 0;
        int max, j = 0;
        for (int i = 1; i <= n + 1 ; i++) {
            max = stairs[j];
            for (j = i - step; j < i; j++) {
                if (j < 0) j = 0;
                if (max < stairs[j]) max = stairs[j];
            }
            stairs[i] += max;
        }
        out.print(stairs[n + 1]);
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
