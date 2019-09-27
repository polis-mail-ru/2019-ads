package ru.mail.polis.ads.task1.art241111;

import java.io.*;
import java.util.Scanner;


/**
    Link to result: https://www.e-olymp.com/ru/submissions/5712059
 */

public final class ASimpleTask {
    private static String firstTask(int inNumber){
        String rString = "";

        // Calculation
        int tenth = inNumber / 10;
        int unit = inNumber % 10;

        // Create string and return it
        rString = tenth + " " + unit;
        return rString;
    }

    public static void main(String[] args) {
        // Initialization some help methodS
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        // Read data
        int inNumber = in.nextInt();
        out.println(firstTask(inNumber));

        // Write result
        out.flush();
    }


}
