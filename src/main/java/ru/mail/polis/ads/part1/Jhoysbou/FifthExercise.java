package ru.mail.polis.ads.part1.Jhoysbou;
import java.util.LinkedList;
import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5723170

public class FifthExercise {
    public static void main(String... args){
        SimpleQueue.run();
    }


    private static class SimpleQueue {
        private static LinkedList<Integer> linkedList = new LinkedList<>();
        private static boolean isRunning = false;
        private static String functionArgument;


        private static void run(){
            isRunning = true;
            Scanner scanner = new Scanner(System.in);
            while (isRunning){
                functionArgument = scanner.nextLine();
                SimpleQueue.execute();
            }
        }

        private static void execute(){
            int length = functionArgument.length();
            String command = "";
            int commandArgument = 0;
            boolean isArgumnt = false;
            for (int i = 0; i < length; ++i){
                if (functionArgument.charAt(i) == ' ')
                {
                    command = functionArgument.substring(0, i);
                    commandArgument = Integer.parseInt(functionArgument.substring(i+1));
                    isArgumnt = true;
                }
            }
            if (!isArgumnt) { command = functionArgument; }

            switch (command){
                case "push":
                    linkedList.add(commandArgument);
                    System.out.println("ok");
                    break;
                case "pop":
                    System.out.println(linkedList.pop());
                    break;
                case "front":
                    System.out.println(linkedList.peekFirst());
                    break;
                case "size":
                    System.out.println(linkedList.size());
                    break;
                case "clear":
                    linkedList.clear();
                    System.out.println("ok");
                    break;
                case "exit":
                    isRunning = false;
                    System.out.println("bye");
                    break;
            }
        }
    }
}
