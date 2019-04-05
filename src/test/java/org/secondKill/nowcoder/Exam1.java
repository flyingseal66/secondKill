package org.secondKill.nowcoder;


import java.math.BigDecimal;
import java.util.Stack;

public class Exam1 {
    public static void main(String[] args) {
        Exam1 exam1 = new Exam1();
        int[] a = {4,5,7,8,15,9996,1,2,3};
        int result = exam1.minNumberInRotateArray(a);
        System.out.println(result);
    }
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) return 0;
        int result = 0;
        int a = 0;
        a = calculateScope(array, 0, array.length-1, result);
        return array[a];
    }

    public int calculateScope(int[] arr, int left, int right, int result){
        if (left == right) {
            result = left;
            return result;
        }
        if (left + 1 == right) {
            result = arr[left] < arr[right] ? left: right;
            return result;
        }

        int mid = (left + right)/2;
            if (arr[mid] < arr[left]) {
                result = calculateScope(arr, left, mid, result);
            } else if (arr[mid] > arr[left] && arr[mid] < arr[right]){
                result = left;
            } else {
                result = calculateScope(arr, mid + 1, right, result);
            }
        return result;
    }

}