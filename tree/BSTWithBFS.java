package tree;

import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
    }
    public String toString(){
        return ""+this.data+"\n";
    }
}

public class BSTWithBFS {

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

    void insert(int key) {
        root = insert(root, key);
    }

    // DELETE
    Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            // Case 1: leaf
            if (root.left == null && root.right == null) return null;
            // Case 2: one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Case 3: two children
            Node succ = findMin(root.right);
            root.data = succ.data;
            root.right = delete(root.right, succ.data);
        }
        return root;
    }

    void delete(int key) {
        root = delete(root, key);
    }

    // Find minimum value
    Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // LEVEL ORDER TRAVERSAL
    void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.println("Queue at begin "+q);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }

    // DRIVER
    public static void main(String[] args) {

        BSTWithBFS tree = new BSTWithBFS();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int v : values) tree.insert(v);

        System.out.println("Level Order:");
        tree.levelOrder(tree.root);

        System.out.println("\n\nDelete 70");
        tree.delete(70);

        System.out.println("Level Order After Deletion:");
        tree.levelOrder(tree.root);
    }
}
