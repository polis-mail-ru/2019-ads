package ru.mail.polis.ads.part2.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Submission here https://www.e-olymp.com/ru/submissions/5785251

public class FifthExercise {
    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        String line = "";
        String command;
        int argument = 0;

        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] temp = line.split(" ");
            command = temp[0];
            if (temp.length > 1) {
                argument = Integer.parseInt(temp[1]);
            }
            switch (command) {
                case "push":
                    stack.push(argument);
                    System.out.println("ok"); // Noncompliant
                    break;
                case "pop":
                    if (stack.empty()){
                        System.out.println("error"); // Noncompliant
                    } else {
                        System.out.println(stack.pop()); // Noncompliant
                    }
                    break;
                case "back":
                    if (stack.empty()) {
                        System.out.println("error"); // Noncompliant
                    } else {
                        System.out.println(stack.peek()); // Noncompliant
                    }
                    break;
                case "size":
                    System.out.println(stack.size()); // Noncompliant
                    break;
                case "clear":
                    stack.clear();
                    System.out.println("ok"); // Noncompliant
                    break;
                case "exit":
                    System.out.println("bye"); // Noncompliant
                    return;
            }
        }
    }
}
