package ru.mail.polis.ads.part1.atani20;
public class SimpleTask{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println( num / 10 + " "+ num % 10);
    }
}