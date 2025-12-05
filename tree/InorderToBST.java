package tree;

import java.util.*;

class Bag {
    int val;
    Bag left, right;
    Bag(int val) {
        this.val = val;
        left = right = null;
    }
}

public class InorderToBST {

    // Build balanced BST from inorder traversal
    public static Bag buildBST(int[] inorder, int start, int end) {
        if(start > end) return null;
        int mid = (start + end) / 2;
        Bag root = new Bag(inorder[mid]);
        root.left = buildBST(inorder, start, mid - 1);
        root.right = buildBST(inorder, mid + 1, end);
        return root;
    }

    // Preorder traversal
    public static void preorder(Bag root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        for(int i=0; i<n; i++) {
            inorder[i] = sc.nextInt();
        }
        Bag root = buildBST(inorder, 0, n-1);
        preorder(root);
        sc.close();
    }
}
