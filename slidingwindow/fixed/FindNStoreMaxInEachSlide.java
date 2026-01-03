package slidingwindow.fixed;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// Heart rate
public class FindNStoreMaxInEachSlide {
    public static int[] findMax(int[] arr, int window){
        int[] result = new int[arr.length-window+1];
        Deque<Integer> deque = new LinkedList<>();
        for(int index = 0;index < arr.length; index++){
            // check to remove peekFirst
            if(!deque.isEmpty()&&deque.peekFirst()<index-window+1){
                deque.pollFirst();
            }
            // check curernt is > peekLast, poll peekLast
            if(!deque.isEmpty()&&arr[deque.peekLast()]<=arr[index]){
                deque.pollLast();
            }
            // adding to deque
            deque.offerLast(index);
            // when to insert value in result array
            if(index>=window-1){
                result[index-window+1] = arr[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {70, 85, 60, 95, 82, 74, 90};
        int window = 2;
        int[] got = findMax(arr, window);
        System.out.println(Arrays.toString(got));
    }
}
