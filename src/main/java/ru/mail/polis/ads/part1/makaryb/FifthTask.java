package ru.mail.polis.ads.part1.makaryb;

// 6125

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Made by БорискинМА
 * 29.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5742838
 */
public final class FifthTask {

    private static String x;

    private static int start;

    static {
        start = 0;
        x = "";
    }

    private static List<Integer> queue = new ArrayList<>();

    private FifthTask() {}

    private static void solve(final Scanner in, final PrintWriter out) {
        do {
            x = in.nextLine();
            switch (x.split(" ")[0]) {
                case "push":
                    push(x, out);
                    break;
                case "pop":
                    pop(out);
                    break;
                case "front":
                    front(out);
                    break;
                case "size":
                    size(out);
                    break;
                case "clear":
                    clear(out);
                    break;
                default:
                    break;
            }
        }
        while(!x.equals("exit"));
        out.println("bye");

        out.flush();
    }

    private static void push(final String x, final PrintWriter out) {
        queue.add(Integer.parseInt(x.split(" ")[1]));
        out.println("ok");
    }

    private static void pop(final PrintWriter out) {
        out.println(queue.get(start));
        queue.remove(start);
    }

    private static void front(final PrintWriter out) {
        out.println(queue.get(start));
    }

    private static void size(final PrintWriter out) {
        out.println(queue.size());
    }

    private static void clear(final PrintWriter out) {
        queue.clear();
        out.println("ok");
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
