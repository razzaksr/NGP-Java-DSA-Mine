package tree;

/* 
🌳 What is a Path in a Binary Tree?
- A path is any sequence of nodes connected by edges, starting at one node and ending at another.
- The path can go downward (parent → child) and may pass through the root or not.
- Example: In a tree, a path could be 4 → 2 → 1 → 3.

💡 What is the Maximum Path Sum?
- Each node has a value (positive or negative).
- The path sum is the sum of values along a path.
- The maximum path sum is the largest possible sum among all paths in the tree.
- Important: The path does not need to pass through the root; it can start and end anywhere.

*/

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class MaxPathSum {
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    // Recursive helper function
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Compute max gain from left and right subtrees
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Price of starting a new path at this node
        int priceNewPath = node.val + leftGain + rightGain;

        // Update global maxSum if new path is better
        maxSum = Math.max(maxSum, priceNewPath);

        // Return max gain to parent (choose one side)
        return node.val + Math.max(leftGain, rightGain);
    }

    // Example usage
    public static void main(String[] args) {
        // Build sample tree
        // TreeNode root = new TreeNode(-10);
        // root.left = new TreeNode(9);
        // root.right = new TreeNode(20);
        // root.right.left = new TreeNode(15);
        // root.right.right = new TreeNode(7);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        MaxPathSum solution = new MaxPathSum();
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root));
    }
}