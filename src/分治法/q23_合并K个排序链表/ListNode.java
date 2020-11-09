package 分治法.q23_合并K个排序链表;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-10-31
 * Time: 14:28
 * Description: Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
