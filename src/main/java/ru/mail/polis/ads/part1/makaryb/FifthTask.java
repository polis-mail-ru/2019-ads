package ru.mail.polis.ads.part1.makaryb;

// 6125

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Made by БорискинМА
 * 29.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5742838
 */
public final class FifthTask {

    private static Logger logger = Logger.getLogger(FifthTask.class.getName());

    private static String x;

    private static int start;

    static {
        start = 0;
        x = "";
    }

    private static List<Integer> queue = new ArrayList<>();

    private FifthTask() {}

    private static void solve(final Scanner in) {
        do {
            x = in.nextLine();
            switch (x.split(" ")[0]) {
                case "push":
                    push(x);
                    break;
                case "pop":
                    pop();
                    break;
                case "front":
                    front();
                    break;
                case "size":
                    size();
                    break;
                case "clear":
                    clear();
                    break;
                default:
                    break;
            }
        }
        while(!"exit".equals(x));
        logger.log(Level.INFO,"bye");

    }

    private static void push(final String x) {
        queue.add(Integer.parseInt(x.split(" ")[1]));
        logger.log(Level.INFO, "ok");
    }

    private static void pop() {
        final String result = String.valueOf(queue.get(start));
        logger.log(Level.INFO, result);
        queue.remove(start);
    }

    private static void front() {
        final String result = String.valueOf(queue.get(start));
        logger.log(Level.INFO, result);
    }

    private static void size() {
        final String result = String.valueOf(queue.size());
        logger.log(Level.INFO, result);
    }

    private static void clear() {
        queue.clear();
        logger.log(Level.INFO,"ok");
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        solve(in);
    }
}
