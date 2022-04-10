/**
 * @description:
 * @Date on 2022/4/10
 * @author:Suche
 **/

public class TestSort {


    public static void main(String[] args) {
        int[] arr = {12, 22, 33, 7, 8, 1, 5, 6, 10};
        insertSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void Sort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            boolean flag = true;
            for (int i = 0; i < arr.length - 1 - i; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = false;
                    int temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
            }
            if (flag)
                break;
        }
    }

    private static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insert = arr[i];
            int j = i - 1;
            while (j >= 0 && insert < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insert;
        }
    }
}
