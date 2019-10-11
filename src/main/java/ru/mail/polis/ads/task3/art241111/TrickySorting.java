package ru.mail.polis.ads.task3.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5834108
 */

public class TrickySorting {
    private static void swap(int[] array, int firstPosition,int secondPosition){
        int num = array[firstPosition];
        array[firstPosition] = array[secondPosition];
        array[secondPosition] = num;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Read count and array
        int countN = in.nextInt();
        int[] arrayForSort = new int[countN];

        for (int i = 0; i < countN; i++) {
            arrayForSort[i] = in.nextInt();
        }

        // Main

        int i = 0;
        while (i < countN-1) {
            if((arrayForSort[i] % 10 > arrayForSort[i + 1] % 10) ||
                    ((arrayForSort[i] % 10 == arrayForSort[i + 1] % 10) && (arrayForSort[i] > arrayForSort[i+1]))){
                swap(arrayForSort,i,i+1);
                if(i > 0) i--;
            } else i++;
        }

        // Write array
        for (i = 0; i < countN; i++) {
            out.print(arrayForSort[i] + " ");
        }
        out.flush();

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
/*
procedure Swap(var a, b : Integer);
var t : Integer;
begin
  t := a; a := b; b := t;
end;

var
  n, i : Integer;
  a : array [0..99] of Integer;
begin
  ReadLn(n);
  for i := 0 to n-1 do Read(a[i]); ReadLn;
  i := 0;
  while i < n-1 do
    begin
      if a[i] mod 10 > a[i+1] mod 10 then
        begin
          Swap(a[i], a[i+1]);
          if i > 0 then i -= 1;
        end
      else if (a[i] mod 10 = a[i+1] mod 10) and (a[i] > a[i+1]) then
        begin
          Swap(a[i], a[i+1]);
          if i > 0 then i -= 1;
        end
      else
        i += 1;
    end;
  Write(a[0]); for i := 1 to n-1 do Write(#32, a[i]); WriteLn;
end.
 */