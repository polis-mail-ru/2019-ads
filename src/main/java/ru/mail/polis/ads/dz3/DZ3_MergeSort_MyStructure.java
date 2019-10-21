package ru.mail.polis.ads.dz3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DZ3_MergeSort_MyStructure {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ThreeValueMap[] arr = new ThreeValueMap[k];
        for (int i = 0; i < k; i++) {
            String[] s = br.readLine().split(" ");
            ThreeValueMap myValue = new ThreeValueMap(i, Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            arr[i] = myValue;
        }
        int left = 0;
        int right = arr.length - 1;
        sortMyStruct(arr, left, right);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].key == arr[i + 1].key) {
                if (arr[i].index > arr[i + 1].index) {
                    ThreeValueMap c = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = c;
                }
            }
        }
        for (ThreeValueMap i : arr) {
            System.out.println(i.key + " " + i.value);
        }
    }

    private static void sortMyStruct(ThreeValueMap[] arr, int left, int right) {
        if (arr.length == 0) {
            return;
        }
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        int opora = arr[middle].key;

        int i = left, j = right;
        while (i <= j) {
            while (arr[i].key < opora) {
                i++;
            }

            while (arr[j].key > opora) {
                j--;
            }

            if (i <= j) {
                ThreeValueMap temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }

            if (left < j) {
                sortMyStruct(arr, left, j);
            }
            if (right > i) {
                sortMyStruct(arr, i, right);
            }
        }
    }
}

class ThreeValueMap {
    int index, key, value;

    public ThreeValueMap(int index, int key, int value) {
        this.index = index;
        this.key = key;
        this.value = value;
    }
}