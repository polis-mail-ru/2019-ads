package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Task5 {

    private static final int ARRAY_SIZE = 100;

    public static void main(final String[] arg) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[ARRAY_SIZE];

        int sizeIndex = 0;
        int first = 0;

        String[] command = new String[0];
        try {
            command = reader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!command[0].equalsIgnoreCase("exit")) {

            switch (command[0]) {
                case "push":
                    array[(sizeIndex + first) % ARRAY_SIZE] = Integer.parseInt(command[1]);
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
            try {
                command = reader.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("bye");
    }
}
