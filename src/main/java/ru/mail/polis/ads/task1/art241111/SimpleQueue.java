package ru.mail.polis.ads.task1.art241111;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5724110
 */


public class SimpleQueue {
    /*
        push n - add to queue
        pop - delete and write first element
        front - only write element
        size - size queue
        clear - delete all elements
        exit - end of program
     */
    private static int getIntFromString(String addElement){
        return Integer.parseInt(addElement.split(" ")[1]);
    }
    private static void pushElement(List<Integer> queue, int position, String addElement){
        queue.add(getIntFromString(addElement));
    }
    public static void main(String[] args) {
        // Initialization some help methodS
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        // Read data
        List<Integer> queue = new ArrayList<Integer>();
        String inString = "";

        //boolean isClear = true;

        int positionEnd = 0,positionBegin = 0;
        do{
            inString = in.nextLine();
            switch (inString.split(" ")[0]) {
                case ("push"):
                    pushElement(queue,positionEnd,inString);
                    positionEnd = positionEnd + 1;

                    out.println("ok");
                    break;

                case "pop":
                    out.println(queue.get(positionBegin));
                    queue.remove(positionBegin);
                    break;

                case "front":
                    out.println(queue.get(positionBegin));
                    break;

                case "size":
                    out.println(queue.size());
                    break;

                case "clear":
                    queue.clear();

                    out.println("ok");
                    break;
            }
        }while(!inString.equals("exit"));

        out.println("bye");

        // Write result
        out.flush();
    }
}
