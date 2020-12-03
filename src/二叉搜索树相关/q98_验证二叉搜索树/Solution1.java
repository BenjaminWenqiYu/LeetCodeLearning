package 二叉搜索树相关.q98_验证二叉搜索树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-12-03
 * Time: 09:17
 * Description:
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class Solution1 {
    /**
     * BFS层次遍历参考
     *      时间复杂度：O(N)，每个节点均只遍历了一次
     *      空间复杂度：O(N)，额外借助了队列空间进行存储
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // while循环的每一次处理都是遍历一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 遍历该层的每一个节点
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                if (i + 1 == size) {
                    res.add(treeNode.val);
                }
            }
        }
        return res;
    }

    /**
     * DFS遍历参考
     *      时间复杂度：O(N)，每个节点均只遍历了一次
     *      空间复杂度：O(N)，因为这棵树不是一棵平衡二叉树，二叉树的深度最少是logN，
     *                 在最化的情况下回退化到N，因此递归时使用的栈空间是 O(N)
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // 先访问根节点 -> 右子树节点 -> 左子树节点
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点
        // 又因为每次我们先访问右子树，所以当前节点一定是最右面的一个节点，因此将当前节点加入res中
        if (depth == res.size()) {
            res.add(node.val);
        }
        depth++;
        dfs(res, node.right, depth);
        dfs(res, node.left, depth);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(6);
        node3.left = node5;
        node1.right = node3;
        node2.right = node4;
        root.left = node1;
        root.right = node2;

        System.out.println(new Solution1().rightSideView2(root));
    }
}
