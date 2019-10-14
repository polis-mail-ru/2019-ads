package ru.mail.polis.ads.part1.kara111.queue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        Scanner scanner = new Scanner(System.in);
        String[] in;
        boolean x = true;
        while (x) {
            in = scanner.nextLine().trim().split(" ");
            switch (in[0]) {
                case "push": {
                    queue.push(Integer.parseInt(in[1]));
                    break;
                }
                case "pop": {
                    queue.pop();
                    break;
                }
                case "front": {
                    queue.front();
                    break;
                }
                case "size": {
                    queue.size();
                    break;
                }
                case "clear": {
                    queue.clear();
                    break;
                }
                case "exit": {
                    System.out.println("bye");
                    x = false;
                    break;
                }

            }
        }


    }

}






