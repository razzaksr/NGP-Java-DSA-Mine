package PST.greedy;

public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;   // Net gas after completing the circuit
        int currTank = 0;    // Current gas in tank while traversing
        int startIndex = 0;  // Candidate starting station

        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i];
            currTank += gas[i] - cost[i];

            // If at any point current tank goes negative,
            // we cannot start from 'startIndex'
            if (currTank < 0) {
                startIndex = i + 1; // Next station becomes candidate
                currTank = 0;       // Reset current tank
            }
        }

        // If total gas is less than total cost, impossible to complete circuit
        return totalTank >= 0 ? startIndex : -1;
    }   
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}