package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/5740108

public final class Task4 {

    private static class Queue{
        private List<Integer> array;

        Queue(){
            array = new ArrayList<>();
        }

        void push(int n){
            array.add(n);
        }

        int pop(){
            return array.remove(0);
        }

        int size(){
            return array.size();
        }

        int front(){
            return array.get(0);
        }

        void clear(){
            array.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        while (true){
            String command = in.next();
            if ("push".equals(command)){
                queue.push(in.nextInt());
                out.println("ok");
            }
            else if ("pop".equals(command)){
                out.println(queue.pop());
            }
            else if ("front".equals(command)){
                out.println(queue.front());
            }
            else if ("size".equals(command)){
                out.println(queue.size());
            }
            else if ("clear".equals(command)){
                queue.clear();
                out.println("ok");
            }
            else if ("exit".equals(command)){
                break;
            }
        }
        out.println("bye");
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

