package ru.mail.polis.ads.part4.bardaev;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Task4074 {
    private Task4074() {}

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(final String[] arg) throws IOException {

        ArrayList<Integer> arr = new ArrayList<>();
        Comparator<Integer> cmp = (o1, o2) -> o1.compareTo(o2);

        String str = null;
        while ((str = in.readLine()) != null) {
            arr.add(Integer.parseInt(str));
            arr.sort(cmp);
            if (arr.size() % 2 == 0) {
                out.println((arr.get(((arr.size() / 2) - 1)) + arr.get((arr.size() / 2))) / 2);
            } else {
                out.println(arr.get(arr.size() / 2));
            }
            out.flush();
        }
    }

}
