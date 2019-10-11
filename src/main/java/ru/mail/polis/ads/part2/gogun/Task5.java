package ru.mail.polis.ads.part2.gogun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Task5 {
    public static void solve(BufferedReader bufferedReader) throws IOException {
        String input = "";
        LinkedList<String> linkedList = new LinkedList<>();
        while (!input.equals("exit")) {
            input = bufferedReader.readLine();
            switch (input.split(" ")[0]) {
                case ("push"):
                    linkedList.push(input.split(" ")[1]);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.pop());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case ("back"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.getFirst());
                    } else {
                        System.out.println("error");
                    }
                    break;
                case ("size"):
                    System.out.println(linkedList.size());
                    break;
                case ("clear"):
                    linkedList.clear();
                    System.out.println("ok");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            solve(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bye");
    }
}

