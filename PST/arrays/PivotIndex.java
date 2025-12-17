package PST.arrays;

import java.util.Arrays;

public class PivotIndex {
    public static int findPivot(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        int partSum = 0;
        for (int index = 0; index < arr.length; index++) {
            if (partSum == totalSum - partSum - arr[index]) 
                return index;
            partSum += arr[index];
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(findPivot(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(findPivot(new int[]{-7, 1, 5, 2, -4, 3, 0}));
        System.out.println(findPivot(new int[]{0, -3, 5, -4, -2, 3, 1, 0}));
    }

}
