package net.microsoft.java.datastruct;

import java.util.Arrays;

/**
 * @description:
 * @Date on 2022/5/3
 * @author: suche
 **/

public class SortTest {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
    }

    public static void quickSort(int[] arr, int start, int end) {
        int k = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] < k)
                left++;
            arr[left] = arr[right];
            while (right > left && arr[left] > k)
                right--;
            arr[right] = arr[left];
        }
        arr[left] = k;

    }
}
