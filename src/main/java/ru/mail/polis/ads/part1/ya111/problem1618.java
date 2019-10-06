import java.util.*;
//https://www.e-olymp.com/ru/submissions/5788926
public final class problem1618 {
    private problem1618(){
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length1 = sc.nextInt();
        int[] Array1 = new int[length1+1];
        for (int i = 1; i <= length1; ++i)
        {
            Array1[i] = sc.nextInt();
        }
        int length2 = sc.nextInt();
        int[] Array2 = new int[length2+1];
        for (int i = 1; i <= length2; ++i)
        {
            Array2[i] = sc.nextInt();
        }

        int [][] result=new int[2][length2+1];
        for (int i = 1; i <= length1; ++i)
            for (int j = 1; j <= length2; ++j) {
                if (Array1[i]==Array2[j])
                    result[i % 2][j] = 1 + result[(i - 1) % 2][j-1];
                else
                    result[i % 2][j] = Math.max(result[(i - 1) % 2][j], result[i % 2][j - 1]);
            }


        System.out.println(result[length1%2][length2]);
    }
}
