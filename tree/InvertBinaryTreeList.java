package tree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class InvertBinaryTreeList {

    // Build tree from level-order list
    public static TreeNode buildTree(List<Integer> levelList) {
        if (levelList == null || levelList.isEmpty()) return null;

        TreeNode root = new TreeNode(levelList.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < levelList.size()) {
            TreeNode node = queue.poll();

            // Left child
            if (i < levelList.size() && levelList.get(i) != null) {
                node.left = new TreeNode(levelList.get(i));
                queue.add(node.left);
            }
            i++;

            // Right child
            if (i < levelList.size() && levelList.get(i) != null) {
                node.right = new TreeNode(levelList.get(i));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    // Invert tree recursively
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // Convert tree back to level-order list
    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                result.add(null);
            }
        }

        // Trim trailing nulls
        int last = result.size() - 1;
        while (last >= 0 && result.get(last) == null) {
            result.remove(last);
            last--;
        }
        return result;
    }

    public static void main(String[] args) {
        // List<Integer> levelList = Arrays.asList(4, 2, 7, 1, 3, 6, 9);
        // List<Integer> levelList = Arrays.asList(2,1,3);
        // List<Integer> levelList = Arrays.asList();
        // List<Integer> levelList = Arrays.asList(1,2);
        List<Integer> levelList = Arrays.asList(5,3,6,2,null,null,7);

        System.out.println("Original: " + levelList);

        TreeNode root = buildTree(levelList);
        TreeNode invertedRoot = invertTree(root);

        List<Integer> invertedList = treeToList(invertedRoot);
        System.out.println("Inverted: " + invertedList);
    }
}