package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DutchManFlagAlgorithm {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(Arrays.asList(0,2,1,0,2,1,1,0,2,1,0));
        System.out.println(queue);
        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            result.add(queue.poll());
        }
        System.out.println(result);
    }
}
