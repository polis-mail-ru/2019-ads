package main.java.ru.mail.polis.ads.part1.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.e-olymp.com/ru/submissions/5858495
 */

public class Task5 {
    private static void solve(final BufferedReader in) throws IOException {

        List<Integer> list = new LinkedList<>();
        String[] commands = in.readLine().split(" ");
        while (!"exit".equals(commands[0])) {
            switch (commands[0]) {
                case ("push"):
                    Integer number = Integer.parseInt(commands[1]);
                    list.add(number);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    System.out.println(list.get(0));
                    list.remove(0);
                    break;
                case ("front"):
                    System.out.println(list.get(0));
                    break;
                case ("size"):
                    System.out.println(list.size());
                    break;
                case ("clear"):
                    list.clear();
                    System.out.println("ok");
                    break;
            }
            commands = in.readLine().split(" ");
        }
        System.out.println("bye");
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        solve(in);
    }
}
