package qyh.sortalgcollection;

import java.util.Arrays;

/**
 * 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int target = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            if (arr[i] <= target) {
                i++;
            } else if (arr[j] >= target) {
                j--;
            } else {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, start, i);
        sort(arr, start + 1, i - 1);
        sort(arr, i + 1, end);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
