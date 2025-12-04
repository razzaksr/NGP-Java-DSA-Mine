package tree;

import java.util.LinkedList;
import java.util.Queue;

class AVLNode {
    int key, height;
    AVLNode left, right;
    AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLTree {
    // ---------------------- UTILITY METHODS --------------------------
    int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }
    int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    // ---------------------- ROTATIONS --------------------------
    // Right rotate (LL Rotation)
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        // Rotation
        x.right = y;
        y.left = T2;
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x; // new root
    }
    // Left rotate (RR Rotation)
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        // Rotation
        y.left = x;
        x.right = T2;
        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y; // new root
    }

    // ---------------------- INSERTION --------------------------
    AVLNode insert(AVLNode node, int key) {
        // 1. Normal BST insertion
        if (node == null) return new AVLNode(key);
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // duplicates not allowed
        // 2. Update height
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        // 3. Get balance factor
        int balance = getBalance(node);
        // 4. Apply rotations if unbalanced
        // LL Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        // RR Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        // LR Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // ---------------------- DELETE --------------------------
    AVLNode delete(AVLNode root, int key) {
        // 1. Perform normal BST deletion
        if (root == null) return root;
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // Node with 1 or 0 children
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = (root.left != null) ? root.left : root.right;
                if (temp == null) { // no child
                    temp = root;
                    root = null;
                } else 
                    root = temp; // one child
            }
            else {
                // Node with 2 children (inorder successor)
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }
        // if tree had 1 node
        if (root == null) return root;
        // 2. Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        // 3. Balance factor
        int balance = getBalance(root);
        // 4. Balance Cases
        // LL Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        // LR Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // RR Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        // RL Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    // ---------------------- TRAVERSALS --------------------------
    void levelOrder(AVLNode root) {
        if (root == null) return;
        Queue<AVLNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            AVLNode node = q.poll();
            System.out.print(node.key + " ");
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }

    // ---------------------- MAIN DRIVER --------------------------
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        AVLNode root = null;
        int[] values = {30, 20, 40, 10, 25, 35, 50};
        for (int v : values) {
            root = tree.insert(root, v);
        }
        System.out.print("\nLevel Order: ");
        tree.levelOrder(root);
        // // Delete an element
        // root = tree.delete(root, 40);
        // System.out.print("\nAfter deletion (Level Order): ");
        // tree.levelOrder(root);
    }
}