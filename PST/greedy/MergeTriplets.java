package PST.greedy;

public class MergeTriplets {
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean a=false, b=false, c=false;
        for (int[] t : triplets) {
            if (t[0] <= target[0] && t[1] <= target[1] && t[2] <= target[2]) {
                if (t[0] == target[0]) a = true;
                if (t[1] == target[1]) b = true;
                if (t[2] == target[2]) c = true;
            }
        }
        return a && b && c;
    }
    public static void main(String[] args) {
        System.out.println(mergeTriplets(new int[][]{{2,5,3},{1,8,4},{1,7,5}}, new int[]{2,7,5}));
        System.out.println(mergeTriplets(new int[][]{{3,4,5},{4,5,6}}, new int[]{3,2,5}));
        System.out.println(mergeTriplets(new int[][]{{2,5,3},{2,3,4},{1,2,5},{5,2,3}}, new int[]{5,5,5}));
    }
}
/* 
Merge Triplets to Form Target Triplet
Stepwise Explanation
- For each triplet, check if it can contribute:
- Only consider triplets where each value ≤ target’s corresponding value.
- Track whether we can achieve target’s x, y, z individually.
- If all three achieved → return true.

Example Trace (Input: triplets=[[2,5,3],[1,8,4],[1,7,5]], target=[2,7,5])
- First triplet [2,5,3] → valid, contributes x=2.
- Second triplet [1,8,4] → invalid (8>7).
- Third triplet [1,7,5] → valid, contributes y=7, z=5.
- Achieved all → true.
*/