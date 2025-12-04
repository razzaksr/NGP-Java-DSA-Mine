package tree;

import java.util.LinkedList;
import java.util.Queue;

class AVLPack{
    int data;
    int height;
    AVLPack left, right;
    public AVLPack(int value){
        this.data = value;
        this.height = 1;
        this.left = this.right = null;
    }
}

public class AVLMyTry {
    private AVLPack root;
    // find height
    private int getHeight(AVLPack node){
        return (node==null)?0:node.height;
    }
    //find balance factor
    private int getBalanceFactor(AVLPack node){
        return (node==null)?0:getHeight(node.left)-getHeight(node.right);
    }
    // rightRotation for LL insertion
    private AVLPack rightRotation(AVLPack node){
        AVLPack nodesLeft = node.left;
        AVLPack leftsRight = nodesLeft.right;
        // rotate
        nodesLeft.right = node;
        node.left = leftsRight;
        // update left heights of node and nodeLeft
        node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
        nodesLeft.height = Math.max(getHeight(nodesLeft.left), getHeight(nodesLeft.right))+1;
        return nodesLeft;
    }
    // leftRotation for RR insertion
    private AVLPack leftRotation(AVLPack node){
        AVLPack nodesRight = node.right;
        AVLPack rightsLeft = nodesRight.left;
        // rotate
        nodesRight.left = node;
        node.right = rightsLeft;
        // update heights of node and nodesRight
        node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
        nodesRight.height = Math.max(getHeight(nodesRight.left), getHeight(nodesRight.right))+1;
        return nodesRight;
    }
    // insert like BST
    private AVLPack insert(AVLPack node, int value) {
        if (node == null) return new AVLPack(value);
        if (value < node.data)
            node.left = insert(node.left, value);
        else if (value > node.data)
            node.right = insert(node.right, value);
        else
            return node;// not added due to duplicate
        // update height of current root
        node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
        // get balance factor
        int factor = getBalanceFactor(node);
        // check factor and apply required rotation if needed
        // LL insertion lead right rotation
        if(factor > 1 && value < node.left.data) return rightRotation(node);
        // RR insertion lead left rotation
        if(factor<-1 && value > node.right.data) return leftRotation(node);
        // LR insertion lead left and followed by right
        if(factor > 1 && value > node.left.data){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        // RL insertion lead right and followed by left
        if(factor < -1 && value < node.right.data){
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }
    // Build BST from array
    private void buildFromArray(int[] arr) {
        for (int x : arr) root = insert(root, x);
    }
    // LEVEL ORDER TRAVERSAL
    private void levelOrder(AVLPack root) {
        if (root == null) return;
        Queue<AVLPack> q = new LinkedList<>();
        q.offer(root);
        System.out.println("Queue at begin "+root.data);
        while (!q.isEmpty()) {
            AVLPack curr = q.poll();
            System.out.print(curr.data + " ");
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
    }
    public static void main(String[] args) {
        int[] values = {30, 20, 10, 40, 50, 25, 35};
        AVLMyTry myTry = new AVLMyTry();
        myTry.buildFromArray(values);
        System.out.println("\nLevel Order ");
        myTry.levelOrder(myTry.root);
    }
}
