import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
public class problem6125 {
    private problem6125(){
    }

    public static void main(String[] args) throws IOException {
        LinkedList<String> myArrayDeque = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean a = true;
        while (a)
        {
            String string = br.readLine();
            switch (string)
            {
                case ("pop"):
                    System.out.println(myArrayDeque.poll());
                    break;

                case ("front"):
                    System.out.println(myArrayDeque.peek());
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