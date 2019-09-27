package ru.mail.polis.ads.part1.Jhoysbou;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// Submission here https://www.e-olymp.com/ru/submissions/5727106

public class FifthExercise {
    public static void main(String... args){
        SimpleQueue.run();
    }

    private static class SimpleQueue {
        private static LinkedList<Integer> arrayList = new LinkedList<>();
        private static boolean isRunning = false;
        private static String functionArgument;


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
                    arrayList.add(commandArgument);
                    System.out.println("ok");
                    break;
                case "pop":
                    System.out.println(arrayList.pop());
                    break;
                case "front":
                    System.out.println(arrayList.peekFirst());
                    break;
                case "size":
                    System.out.println(arrayList.size());
                    break;
                case "clear":
                    arrayList = new LinkedList<Integer>();
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
