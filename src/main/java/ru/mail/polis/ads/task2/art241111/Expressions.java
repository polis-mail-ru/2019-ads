    package ru.mail.polis.ads.task2.art241111;
    import java.io.*;
    import java.util.StringTokenizer;

    /**
             Link to result: https://www.e-olymp.com/ru/submissions/5780408
     **/

    class Index {
        int in;
        Index(int a) {
            in = a;
        }
    }
    public class Expressions{
        private static int ind;
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

        private static void get_levels(char[] Expr, Index index, int depth, String[] Answer) {
            if (Answer[depth] != null){
                Answer[depth] += Expr[index.in];
            } else {
                ind++;
                Answer[depth] = String.valueOf(Expr[index.in]);
            }

            index.in--;
            if (Character.isUpperCase(Expr[index.in + 1])) {
                get_levels(Expr, index, depth + 1, Answer);
                get_levels(Expr, index, depth + 1, Answer);
            }
        }


        public static void main (String[] args)
        {
            final FastScanner in = new FastScanner(System.in);
            PrintWriter out = new PrintWriter(System.out);
            int n = in.nextInt();
            String str;
            StringBuffer stringBufferOut = new StringBuffer();

            for (int k = 0; k < n; k++) {
                str = in.next();
                char[] Expr = str.toCharArray();
                String[] Answer = new String[Expr.length];

                Index index = new Index(Expr.length - 1);
                get_levels(Expr, index, 0, Answer);

                for (int j = ind - 1; j >= 0; j--){
                    stringBufferOut.append(Answer[j]);
                }

                stringBufferOut.append("\n");
                ind = 0;
            }
            out.print(stringBufferOut);
            out.flush();
        }
    }