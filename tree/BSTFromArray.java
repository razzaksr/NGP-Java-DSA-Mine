package tree;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class BSTFromArray {

    Node root;

    // INSERT
    Node insert(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.data)
            root.left = insert(root.left, key);
        else if (key > root.data)
            root.right = insert(root.right, key);

        return root;
    }

    // Build BST from array
    void buildFromArray(int[] arr) {
        for (int x : arr) {
            root = insert(root, x);
        }
    }

    // INORDER (to verify BST)
    void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // DRIVER
    public static void main(String[] args) {

        BSTFromArray tree = new BSTFromArray();

        int[] arr = {40, 20, 10, 30, 60, 50, 70};
        tree.buildFromArray(arr);

        System.out.println("BST built from array (Inorder gives sorted):");
        tree.inorder(tree.root);
    }
}
