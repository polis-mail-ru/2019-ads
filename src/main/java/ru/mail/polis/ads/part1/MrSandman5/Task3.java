package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/5775238

/* Билл пытается компактно представить последовательности прописных символов от A до Z с помощью упаковки повторяющихся подпоследовательностей внутри них.
Например, один из способов представить последовательность AAAAAAAAAABABABCCD - это 10(A)2(BA)B2(C)D.
Он формально определяет сжатые последовательности символов и правила перевода их в несжатый вид следующим образом:

    Последовательность, содержащая один символ от A до Z, является упакованной. Распаковка этой последовательности дает ту же последовательность из одного символа.
    Если S и Q - упакованная последовательность, то SQ - также упакованная последовательность. Если S распаковывается в S′, а Q распаковывается в Q′, то SQ распаковывается в S′Q′.
    Если S - упакованная последовательность, то X(S) - также упакованная последовательность, где X- десятичное представление целого числа, большего 1. Если S распаковывается в S′, то X(S) распаковывается в S′, повторенную X раз.

Следуя этим правилам, легко распаковать любую заданную упакованную последовательность. Однако Биллу более интересен обратный переход.
Он хочет упаковать последовательность так, чтобы результирующая сжатая последовательность содержала наименьшее возможное число символов.

Входные данные
В первой строке находится последовательность символов от A до Z. Длина исходной последовательности от 1 до 100.

Выходные данные
В единственной строке выводится упакованная последовательность наименьшей длины, которая распаковывается в заданную последовательность. Если таких последовательностей несколько, можно выводить любую.
* */

public final class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        if (input.isEmpty()) {
            System.out.println();
            return;
        }
        int n = input.length();
        ArrayList<ArrayList<String>> seq = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            seq.add(new ArrayList<>(n));
        }
        for (ArrayList<String> el : seq) {
            for (int i = 0; i < n; i++) {
                el.add("_");
            }
        }
        for (int len = 1; len <= n; len++){
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                String min = input.substring(left, left + len);
                if (len > 4){
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String tmp = seq.get(left).get(right1) + seq.get(left2).get(right);
                        if (tmp.length() < min.length()){
                            min = tmp;
                        }
                    }
                    for (int p = 1; p < len; p++) {
                        if (len % p == 0){
                            boolean isPeriodic = true;
                            for (int i = left + p; i <= right ; i++) {
                                if (input.charAt(i) != input.charAt(i - p)){
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic){
                                String tmp = len/p + "(" + seq.get(left).get(left + p - 1) + ")";
                                if (tmp.length() < min.length()){
                                    min = tmp;
                                }
                            }
                        }
                    }
                }
                seq.get(left).set(right, min);
            }
        }
        out.print(seq.get(0).get(n - 1));
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
