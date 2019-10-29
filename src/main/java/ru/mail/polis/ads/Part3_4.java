import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
//https://www.e-olymp.com/ru/submissions/5914231
class Part3_4 {

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        int k = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        BigInteger[] arr = new BigInteger[temp.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new BigInteger(temp[i]);
        }

        Arrays.sort(arr);

        out.print(arr[arr.length - k]);
    }

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}