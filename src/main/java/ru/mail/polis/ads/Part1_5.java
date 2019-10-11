import java.io.*;
import java.util.LinkedList;

public final class Part1_5 {
    public static void main(String[] argc) throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String input = "";
        LinkedList<String> linkedList = new LinkedList<>();
        while (!input.equals("exit")) {
            input = bufferedReader.readLine();
            switch (input.split(" ")[0]) {
                case ("push"):
                    linkedList.add(input.split(" ")[1]);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.remove());
                    }
                    break;
                case ("front"):
                    if (linkedList.size() != 0) {
                        System.out.println(linkedList.getFirst());
                    }
                    break;
                case ("size"):
                    System.out.println(linkedList.size());
                    break;
                case ("clear"):
                    linkedList.clear();
                    System.out.println("ok");
                    break;


            }

        }

        System.out.println("bye");
    }
}