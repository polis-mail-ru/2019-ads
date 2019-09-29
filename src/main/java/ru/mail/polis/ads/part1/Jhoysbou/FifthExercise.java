package ru.mail.polis.ads.part1.Jhoysbou;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// Submission here https://www.e-olymp.com/ru/submissions/5743541

public class FifthExercise {
    public static void main(String... args){
        SimpleQueue.run();
    }

    private static class SimpleQueue {
        private static LinkedList<Integer> arrayList = new LinkedList<>();
        private static boolean isRunning = false;
        private static String functionArgument;
        private static int beginIndex = 0;
        private static int endIndex = 0;


        private static void run(){
            isRunning = true;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (isRunning){
                try{
                    functionArgument = reader.readLine();
                }catch (IOException e){
                    e.printStackTrace();
                }
                SimpleQueue.execute();
            }

    }

        private static void execute(){
            int length = functionArgument.length();
            String command = "";
            int commandArgument = 0;
            String[] temp = functionArgument.split(" ");
            command = temp[0];
            try {
                commandArgument = Integer.parseInt(temp[1]);
            }catch(java.lang.ArrayIndexOutOfBoundsException e){ }

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
