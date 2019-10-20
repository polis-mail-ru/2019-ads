package part2;

/*В индийском храме пол прямоугольной формы выложен одинаковыми квадратными плитками 1 х 1, на каждую из которых высыпано от 0 до k зернышек (k ≤ 30000). Размеры пола m х n.
Мышка выбегает из левого нижнего угла пола храма и двигается к входу в другую норку, расположенную в противоположном углу.
Мышка может двигаться только вправо или вперед, собирая все зернышки с плитки, на которой она находится.

Найти маршрут, двигаясь по которому мышка соберет наибольшее количество зернышек.

Входные данные
Первая строка содержит числа m и n – размеры пола (1 ≤ m, n ≤ 100).
Далее идет m строк, начиная сверху, в каждой из которых размещено n чисел – количество зернышек на соответствующей плитке.

Выходные данные
Вывести маршрут движения мышки в формате: RRFFFRF (F – шаг вперед, R – шаг вправо).
* */

//https://www.e-olymp.com/ru/submissions/5847303

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] board = new long[100][100];
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--)
            for (int j = 0; j < m; j++)
                board[i][j] = in.nextLong();

        for(int i = 1; i < n; i++)
            board[i][0] = board[i][0] + board[i-1][0];
        for(int j = 1; j < m; j++)
            board[0][j] = board[0][j] + board[0][j-1];

        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++)
                board[i][j] = board[i][j] + Math.max(board[i - 1][j], board[i][j - 1]);

        int k = n - 1, t = m - 1;
        while (k > 0 || t > 0){
            if (k > 0 && t > 0){
                if (board[k-1][t] > board[k][t-1]){
                    sb.append("F");
                    k--;
                }
                else{
                    sb.append("R");
                    t--;
                }
            }
            else if (k == 0){
                sb.append("R");
                t--;
            }
            else if (t == 0){
                sb.append("F");
                k--;
            }
        }
        out.println(sb.reverse().toString());
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

        long nextLong(){ return Long.parseLong(next());}
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
