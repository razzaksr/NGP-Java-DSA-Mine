package tree;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BalancedBSTFromSortedArray {

    // Build from sorted array
    public static TreeNode buildBalancedBST(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBST(int[] arr, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(arr[mid]);

        root.left = buildBST(arr, start, mid - 1);
        root.right = buildBST(arr, mid + 1, end);

        return root;
    }

    // Inorder Traversal
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = buildBalancedBST(sortedArray);

        System.out.print("Inorder Traversal: ");
        inorder(root); // Output: 1 2 3 4 5 6 7
    }
}
