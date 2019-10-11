package ru.mail.polis.ads.part1.Kopeyka885;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main{

public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    byte number = scanner.nextByte();
    PrintWriter printWriter = new PrintWriter(System.out);
    printWriter.print( (int) number/10 + " " + number%10);
    printWriter.flush();
    }
}