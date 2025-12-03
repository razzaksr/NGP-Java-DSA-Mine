package tree;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class DiameterOfTree {
    private int diameter;

    public int diameterOfTree(TreeNode root) {
        diameter = 0;
        depth(root);
        return diameter;
    }

    // Recursive helper to compute depth
    private int depth(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // Update diameter: longest path through this node
        diameter = Math.max(diameter, leftDepth + rightDepth);

        // Return height of subtree
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // Example usage
    public static void main(String[] args) {
        // Build sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfTree solution = new DiameterOfTree();
        System.out.println("Diameter of Tree: " + solution.diameterOfTree(root));
    }
}

/*
Perfect — let’s walk through the **step‑by‑step recursion trace** for the **Diameter of a Binary Tree** algorithm, so you and your trainees can see exactly how the diameter is updated at each node.

---

## 🌳 Recap: Diameter Definition
- The **diameter** is the length of the longest path between any two nodes in the tree.  
- At each node, the longest path through it is `left_depth + right_depth`.  
- The recursion computes depths bottom‑up, updating the global diameter.

---

## 🧩 Example Tree
We’ll use the same tree:

```
        1
       / \
      2   3
     / \
    4   5
```

---

## 🔎 Step‑by‑Step Trace

### Start at Root (1)
- Call `depth(1)` → must compute `depth(2)` and `depth(3)`.

---

### Node (2)
- Call `depth(2)` → must compute `depth(4)` and `depth(5)`.

#### Node (4)
- `depth(4)` → children are null → returns `1`.  
- LeftDepth = 0, RightDepth = 0 → diameter candidate = `0 + 0 = 0`.  
- Diameter remains `0`.

#### Node (5)
- `depth(5)` → children are null → returns `1`.  
- LeftDepth = 0, RightDepth = 0 → diameter candidate = `0 + 0 = 0`.  
- Diameter remains `0`.

#### Back at Node (2)
- LeftDepth = 1 (from node 4), RightDepth = 1 (from node 5).  
- Diameter candidate = `1 + 1 = 2`.  
- Update diameter → `diameter = 2`.  
- Return depth = `1 + max(1, 1) = 2`.

---

### Node (3)
- `depth(3)` → children are null → returns `1`.  
- LeftDepth = 0, RightDepth = 0 → diameter candidate = `0`.  
- Diameter remains `2`.

---

### Back at Root (1)
- LeftDepth = 2 (from node 2), RightDepth = 1 (from node 3).  
- Diameter candidate = `2 + 1 = 3`.  
- Update diameter → `diameter = 3`.  
- Return depth = `1 + max(2, 1) = 3`.

---

## ✅ Final Result
- The recursion finishes.  
- **Diameter = 3** (longest path is `4 → 2 → 1 → 3` or `5 → 2 → 1 → 3`).  

---

## ☕ Java Code (already shared)
```java
public int diameterOfTree(TreeNode root) {
    diameter = 0;
    depth(root);
    return diameter;
}

private int depth(TreeNode node) {
    if (node == null) return 0;
    int leftDepth = depth(node.left);
    int rightDepth = depth(node.right);
    diameter = Math.max(diameter, leftDepth + rightDepth);
    return 1 + Math.max(leftDepth, rightDepth);
}
```

---

✨ This trace shows how **depth values bubble up** and how the **diameter is updated at each node**.  

Would you like me to also prepare a **visual diagram of the recursion tree** (with arrows showing depth returns and diameter updates), so trainees can literally see the flow?

*/