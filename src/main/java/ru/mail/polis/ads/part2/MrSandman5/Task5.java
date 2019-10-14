package part2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/5785762

public class Task5 {
    private static class Stack{
        private List<Integer> array;

        Stack(){
            array = new ArrayList<>();
        }

        void push(int n){
            array.add(n);
        }

        int pop(){
            return array.remove(size() - 1);
        }

        int size(){
            return array.size();
        }

        int back(){
            return array.get(size() - 1);
        }

        void clear(){
            array.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while (true){
            String command = in.next();
            if ("push".equals(command)){
                stack.push(in.nextInt());
                System.out.println("ok");
            }
            else if ("pop".equals(command)){
                if (stack.size() > 0) System.out.println(stack.pop());
                else out.println("error");
            }
            else if ("back".equals(command)){
                if (stack.size() > 0) System.out.println(stack.back());
                else out.println("error");
            }
            else if ("size".equals(command)){
                System.out.println(stack.size());
            }
            else if ("clear".equals(command)){
                stack.clear();
                System.out.println("ok");
            }
            else if ("exit".equals(command)){
                break;
            }
        }
        System.out.println("bye");
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
