package ru.mail.polis.ads.part4.KateMoreva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//e-olymp problem 1457 "Станция "Сортировочная""

public class Task5 {
    private Task5(){
    }
    private static long count;
    private static long weight;
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter output = new PrintWriter(System.out);

    private static boolean possible() throws IOException {
        String str = input.readLine();
        boolean check = true;
        long max = 0;
        long nextNumber;
        int start = 0;
        int space;
        for (int i = 0; i < count; ++i) {
            space = str.indexOf(" ", start);
            if (space == -1) {
               space = str.length();
            }
            nextNumber = Integer.parseInt(str.substring(start, space));
            start = space + 1;
            if (nextNumber < max && (nextNumber + max > weight)) {
                check = false;
                break;
            }
            if (nextNumber > max) {
                max = nextNumber;
            }
        }
        return check;
    }
    private static void solve() throws IOException {
        String str = input.readLine();
        count = Integer.parseInt(str.substring(0, str.indexOf(" ")));
        weight = Long.parseLong(str.substring(str.indexOf(" ") + 1));
        if (possible()) {
            output.print("Yes");
        } else {
            output.print("No");
        }
    }

    public static void main(String[] args) {
        try {
            solve();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
