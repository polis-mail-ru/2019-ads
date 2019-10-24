package ru.mail.polis.ads.valer1435.part5;

// https://www.e-olymp.com/ru/submissions/5925046
import java.util.Scanner;

public class Problem264 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt();
        int[] arr = new int[cnt];
        int[] d = new int[cnt];
        d[0] = 1;
        for (int i = 0; i < cnt; i++){
            arr[i] = in.nextInt();
        }

        int gMax = d[0];
        for (int i = 1; i < cnt; i++){
            int max = 0;
            for (int j=0; j < i; j++){
                if (arr[j] != 0 && arr[i]%arr[j] == 0 && d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max+1;
            if (d[i] > gMax){
                gMax = d[i];
            }
        }
        System.out.println(gMax);
    }
}
