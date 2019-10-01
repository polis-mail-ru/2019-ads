package ru.mail.polis.ads.part1.gogun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class Task5 {
    private static void solve(BufferedReader bufferedReader) throws IOException {
        String input = "";
        LinkedList<String> linkedList = new LinkedList<>();
        while (!input.equals("exit")) {
            input = bufferedReader.readLine();
            switch (input.split(" ")[0]) {
                case ("push"):
                    linkedList.add(input.split(" ")[1]);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.remove());
                    }
                    break;
                case ("front"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.getFirst());
                    }
                    break;
                case ("size"):
                    System.out.println(linkedList.size());
                    break;
                case ("clear"):
                    linkedList.clear();
                    System.out.println("ok");
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] argc){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            solve(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bye");
    }
}
