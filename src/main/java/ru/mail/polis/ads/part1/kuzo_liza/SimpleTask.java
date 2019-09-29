package ru.mail.polis.ads.part1.kuzo_liza;

import java.util.Scanner;

public class SimpleTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(input.replace("", " ").trim());
    }
}
