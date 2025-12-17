package PST.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovingZeros {
    public static void main(String[] args) {
        ArrayList<Integer> nums = (ArrayList<Integer>) Stream.of(3,0,0,1,0,5,0,6,0).collect(Collectors.toList());
        int count = 0;
        while(nums.contains(0)){
            int index = nums.indexOf(0);
            nums.remove(index);
            count++;
        }
        while(count>0){nums.add(0);count--;}
        System.out.println(nums);
    }
}
