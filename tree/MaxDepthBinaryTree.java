package tree;

import java.util.*;

class Cover {
    int val;
    int left;
    int right;
    Cover(int val, int left, int right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaxDepthBinaryTree {

    public static int maxDepth(Cover[] nodes, int index) {
        if(index == -1) return 0;
        Cover node = nodes[index];
        int leftDepth = maxDepth(nodes, node.left);
        int rightDepth = maxDepth(nodes, node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Cover[] nodes = new Cover[n];
        for(int i=0; i<n; i++) {
            int val = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();
            nodes[i] = new Cover(val, left, right);
        }
        System.out.println(maxDepth(nodes, 0));
        sc.close();
    }
}