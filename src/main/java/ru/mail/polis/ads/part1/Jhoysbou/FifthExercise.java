package ru.mail.polis.ads.part1.Jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

// Submission here https://www.e-olymp.com/ru/submissions/5743541

public abstract class FifthExercise {
    public static void main(final String... args){
        SimpleQueue.run();
    }

    private abstract static class SimpleQueue {
        private static List<Integer> arrayList = new LinkedList<>();
        private static boolean isRunning;
        private static String functionArgument;
        private static int beginIndex;
        private static int endIndex;


        private static void run(){
            isRunning = true;
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (isRunning){
                try{
                    functionArgument = reader.readLine();
                }catch (IOException e){
                    isRunning = false;
                    e.printStackTrace();
                }
                SimpleQueue.execute();
            }

    }

        private static void execute(){
            String command = "";
            int commandArgument = 0;
            final String[] temp = functionArgument.split(" ");
            command = temp[0];

            if (temp.length > 1) {
                commandArgument = Integer.parseInt(temp[1]);
            }

            switch (command){
                case "push":
                    arrayList.add(commandArgument);
                    System.out.println("ok");
                    endIndex++;
                    break;
                case "pop":
                    System.out.println(arrayList.get(beginIndex));
                    arrayList.remove(beginIndex);
                    break;
                case "front":
                    System.out.println(arrayList.get(beginIndex));
                    break;
                case "size":
                    System.out.println(arrayList.size());
                    break;
                case "clear":
                    arrayList.clear();
                    System.out.println("ok");
                    break;
                case "exit":
                    isRunning = false;
                    System.out.println("bye");
                    break;
                default:
                    isRunning = false;

            }
        }
    }
}
