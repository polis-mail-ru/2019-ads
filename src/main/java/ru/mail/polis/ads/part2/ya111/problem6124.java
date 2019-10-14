package ru.mail.polis.ads.part2.ya111;

import java.util.Scanner;
public final class problem6124 {
    private problem6124(){
    }

    public static void main(final String[] argc) throws IOException {
        LinkedList<String> myArrayDeque = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean a = true;
        while (a)
        {
            String string = br.readLine();
            switch (string)
            {
                case ("pop"):
                    if (myArrayDeque.peekLast() != null)
                        System.out.println(myArrayDeque.pollLast());
                    else
                        System.out.println("error");
                    break;

                case ("back"):
                    if (myArrayDeque.peekLast() != null)
                        System.out.println(myArrayDeque.peekLast());
                    else
                        System.out.println("error");
                    break;

                case ("size"):
                    System.out.println(myArrayDeque.size());
                    break;

                case ("clear"):
                    myArrayDeque.clear();
                    System.out.println("ok");
                    break;

                case ("exit"):
                    System.out.println("bye");
                    a=false;
                    break;

                default:
                    System.out.println("ok");
                    String[] parts = string.split(" ");
                    myArrayDeque.add(parts[1]);
                    break;
            }
        }
    }
}