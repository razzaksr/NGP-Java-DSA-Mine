package graph;

import java.util.*;

public class CouplesHoldingHands {
    public static int minSwapsCouples(int[] row) {
        int n = row.length;
        // Map person -> seat index
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;
        for (int i = 0; i < n; i += 2) {
            int first = row[i];
            int partner = first % 2 == 0 ? first + 1 : first - 1;

            if (row[i + 1] != partner) {
                swaps++;
                int partnerIndex = pos[partner];

                // Swap row[i+1] with partner
                int temp = row[i + 1];
                row[i + 1] = partner;
                row[partnerIndex] = temp;

                // Update positions
                pos[temp] = partnerIndex;
                pos[partner] = i + 1;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] row = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            row[i] = sc.nextInt();
        }
        System.out.println(minSwapsCouples(row));
        sc.close();
    }
}