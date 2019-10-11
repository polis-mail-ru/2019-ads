import java.io.*;
import java.util.*;

class Part1_1{
    public static void main(String[] args)   {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int a = in.nextInt();
        int b,c;

        b=a/10; c=a%10;
        out.println(b +" " + c);

        out.flush();
    }
}
