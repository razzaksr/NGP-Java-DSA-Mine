package tree;

import java.util.*;

class Box {
    int val;
    int left;
    int right;
    public Box(int val, int left, int right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
}

public class ValidateBST {

    public static boolean isValidBST(Box[] nodes, int index, long min, long max) {
        if(index == -1) return true;
        Box node = nodes[index];
        if(node.val <= min || node.val >= max) return false;
        return isValidBST(nodes, node.left, min, node.val) &&
               isValidBST(nodes, node.right, node.val, max);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Box[] nodes = new Box[n];
        for(int i=0; i<n; i++) {
            int val = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();
            nodes[i] = new Box(val, left, right);
        }
        boolean result = isValidBST(nodes, 0, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.println(result ? "Valid BST" : "Invalid BST");
        sc.close();
    }
}
