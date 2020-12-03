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
            int size = queue.size();
            for (int i = 0; i < size; i++) {
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

        System.out.println(new Solution1().rightSideView(root));
    }
}
