package ru.mail.polis.ads.part1;

import java.util.Scanner;

public final class Task5 {

    public static final int ARRAY_SIZE = 100;

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);

        int[] array = new int[ARRAY_SIZE];

        int sizeIndex = 0;
        int first = 0;

        String command = scanner.next();
        while (!command.equalsIgnoreCase("exit")) {

            switch (command) {
                case "push":
                    array[(sizeIndex + first) % ARRAY_SIZE] = scanner.nextInt();
                    ++sizeIndex;
                    System.out.println("ok");
                    break;
                case "pop":
                    System.out.println(array[first]);
                    first = (1 + first) % 100;
                    --sizeIndex;
                    break;
                case "front":
                    System.out.println(array[first]);
                    break;
                case "size":
                    System.out.println(sizeIndex);
                    break;
                case "clear":
                    first = 0;
                    sizeIndex = 0;
                    System.out.println("ok");
                    break;
            }
            command = scanner.next();
        }

        System.out.println("bye");
    }
}
