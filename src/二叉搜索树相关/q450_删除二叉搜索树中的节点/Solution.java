package 二叉搜索树相关.q450_删除二叉搜索树中的节点;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-19
 * Time: 09:56
 * Description: 用前驱节点代替待删除的节点 o(log(n))
 */
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }
        // 求前驱节点
        TreeNode predecessor = maximum(root.left);
        TreeNode predecessorCopy = new TreeNode(predecessor.val);
        // 先remove再衔接
        predecessorCopy.left = removeMax(root.left);
        predecessorCopy.right = root.right;
        root.left = null;
        root.right = null;
        return predecessorCopy;
    }

    /**
     * 两种情况，一种 node.right == null 说明前驱节点为删除节点的左节点，否则为删除节点的右侧叶节点（对应maximum(root.left)）
     * @param node
     * @return
     */
    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private TreeNode maximum(TreeNode node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.right = n2;
        new Solution().deleteNode(root, 1);
    }
}
