package part3;

// https://www.e-olymp.com/ru/submissions/5864384

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n == 1){
            out.println(0);
            return;
        }
        long[] array = new long[1000];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        int counter = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n - 1 ; j++){
                if(array[j] > array[j+1]){
                    long temp = array[j];
                    array[j] = array[j+1];
                    array[j+1]=temp;
                    counter++;
                }
            }
        }
        out.println(counter);
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
