package dp;

public class EditDistanceViaDP {
    public static int count(String alpha, String beta){
        int rowSize = alpha.length();
        int colSize = beta.length();

        int[][] poss = new int[rowSize+1][colSize+1];

        // fill default values
        for(int index = 0; index <= rowSize; index++){poss[index][0] = index;}
        for(int index = 0; index <= colSize; index++){poss[0][index] = index;}

        // for(int[] row:poss){System.out.println(Arrays.toString(row));}

        // fill the poss
        for(int row = 1; row <= rowSize; row++){
            for(int col=1; col <= colSize; col++){
                if(alpha.charAt(row-1)==beta.charAt(col-1)){
                    poss[row][col]=poss[row-1][col-1];
                }
                else{
                    poss[row][col] = 1 + Math.min(poss[row-1][col-1],Math.min(poss[row][col-1], poss[row-1][col]));
                }
            }
        }

        return poss[rowSize][colSize];
    }
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "rose";
        System.out.println(count(word1, word2));
        word1 = "madam";
        word2 = "mam";
        System.out.println(count(word1, word2));
        word1 = "horse";
        word2 = "ros";
        System.out.println(count(word1, word2));
    }
}
/* 
- Time Complexity: O(m . n)
- Space Complexity: O(m . n)
*/