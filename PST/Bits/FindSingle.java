package PST.Bits;

public class FindSingle {
    public static int find(int[] nums) {
        // Initialize the result to 0. 
        // 0 XOR any number 'A' results in 'A' (A ^ 0 = A).
        int result = 0;

        // Iterate through every element in the array
        for (int num : nums) {
            // Apply the XOR operation.
            // When a number appears twice (A ^ A), it cancels out to 0.
            // The single number (X) remains at the end (0 ^ X = X).
            result ^= num;
            System.out.println(num+" "+result);
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(find(new int[]{4,1,2,1,2}));
    }
}
