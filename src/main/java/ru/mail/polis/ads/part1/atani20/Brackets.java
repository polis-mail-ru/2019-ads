package ru.mail.polis.ads.part1.atani20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Brackets{//SolveTemplate {
    private /*SolveTemplate*/Main() {
        // Should not be instantiated
    }
    static int size = 110;
    static int [][]arr = new int[size][size];
    static int [][]Path = new int[size][size];
    static {
        for(int i =0; i < size; i++)
            for(int j = 0; j < size; j++)
                arr[i][j] = Integer.MAX_VALUE;
        for(int i =0; i < size; i++)
            for(int j = 0; j < size; j++)
                Path[i][j] = -1;
    }
    static private void PrintResult(String str, int from, int to, final PrintWriter out){
        if(from > to)
            return;
        if(from == to){
            if (str.charAt(from) == '[' || str.charAt(to) == ']')
                out.print("[]");
            else
                out.print("()");
        } else if(Path[from][to] == -1){
            out.print(str.charAt(from));
            PrintResult(str, from + 1, to -1, out);
            out.print(str.charAt(to));
        }
        else{
            PrintResult(str, from, Path[from][to], out);
            PrintResult(str, 1 + Path[from][to], to, out);
        }
    }
    static private int RecSolve(String str, int i, int j){
        if(i == j)
            return 1;
        if(i > j)
            return 0;
        if (arr[i][j] != Integer.MAX_VALUE)
            return arr[i][j];
        if ((str.charAt(i) == '(' && str.charAt(j) == ')') || (str.charAt(i) == '[' && str.charAt(j) == ']')){
            int a = RecSolve(str,i+1,j-1);
            arr[i][j] = arr[i][j] < a ? arr[i][j] :a;
        }

        for (int k = i; k < j; k++)
        {
            int temp = RecSolve(str, i,k) + RecSolve(str,k+1,j);
            if (temp < arr[i][j]) {
                Path[i][j] = k;
                arr[i][j] = temp;
            }
        }

        return arr[i][j];
    }
    private static void solve(final com.company.Main.FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int size = str.length();
        int ans = RecSolve(str, 0, size - 1);
        PrintResult(str, 0, size - 1, out);
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
        final com.company.Main.FastScanner in = new com.company.Main.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}