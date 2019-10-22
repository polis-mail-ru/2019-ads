import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public final class problem4827 {



    public static BigInteger findOrderStatistic(BigInteger[] Array, int k) {
        int left = 0, right = Array.length;
        while (true) {
            int mid = partition(Array, left, right);

            if (mid == k) {
                return Array[mid];
            }
            else if (k < mid) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
    }
    static int partition (BigInteger[] Array, int left, int right) {
        BigInteger x = Array[left];
        int j = left;
        for (int i = left + 1; i < right; i++) {
            if (Array[i].compareTo(x) <= 0) {
                j++;
                BigInteger temp = Array[i];
                Array[i] = Array[j];
                Array[j] = temp;
            }
        }
        BigInteger temp = Array[left];
        Array[left] = Array[j];
        Array[j] = temp;

        return j;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine());

        String[] numbers = bf.readLine().split(" ");
        int length = numbers.length;
        BigInteger[] Array = new BigInteger[length];
        for (int i = 0; i < length; i++)
            Array[i] = new BigInteger(numbers[i]);

        System.out.println(findOrderStatistic(Array, Array.length-k));
    }
}