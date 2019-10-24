package ru.mail.polis.ads.valer1435.part5;
// https://www.e-olymp.com/ru/submissions/5939519
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Problem2169 {
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int[] arr = new int[num];
        for (int i=1; i<=num; i++){
            arr[i-1] = i;
        }
        out.println(printArr(arr));
        findNext(arr, 1);
        out.flush();
    }
    public static void findNext(int[] arr, int c){
        while (true) {
            int index = -1;
            int index2 = -1;
            for (int i = arr.length - 2; i >= 0; i--) {
                if (arr[i] < arr[i + 1]) {
                    index = i;
                    for (int j = arr.length - 1; j >= i; j--) {
                        if (arr[j] > arr[i]) {
                            index2 = j;
                            int tmp = arr[index];
                            arr[index] = arr[index2];
                            arr[index2] = tmp;
                            break;
                        }
                    }
                    break;
                }
            }
            if (index == -1) {
                return;
            }
            int[] buf = new int[arr.length - index - 1];
            for (int i = arr.length - 1; i > index; i--) {
                buf[arr.length - i - 1] = arr[i];
            }

            for (int i = index + 1; i < arr.length; i++) {
                arr[i] = buf[i - index - 1];
            }
            out.println(printArr(arr));
        }
    }


    public static String printArr(int[] arr){
        StringBuffer bld = new StringBuffer();
        bld.append(arr[0]);
        for (int i=1; i<arr.length; i++){
            bld.append(" ").append(arr[i]);
        }
        return String.valueOf(bld);
    }

}

