package ru.mail.polis.ads.part1.medalexey;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *  Название задачи: "Простая очередь"
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5728980
 *
 */
public final class SimpleQueue {

    private SimpleQueue() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final Deque<Integer> queue = new ArrayDeque<>();
        String input = "";

        while (!"exit".equals(input)) {
            input = in.next();

            if (input.matches("push")) {
                queue.addLast(in.nextInt());
                out.write("ok\n");
            } else if (input.matches("pop")) {
                out.write(queue.pollFirst() + "\n");
            } else if (input.matches("front")) {
                out.write(queue.peekFirst() + "\n");
            } else if (input.matches("size")) {
                out.write(queue.size() + "\n");
            } else if (input.matches("clear")) {
                queue.clear();
                out.write("ok\n");
            }
        }

        out.write("bye");
        out.flush();

    }


    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        solve(in, out);
        out.close();
        in.close();
    }
}
