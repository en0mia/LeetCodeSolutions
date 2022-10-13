/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 13/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromLinkedList {
    private int gradeFromEnd = -1;
    private boolean removed = false;

    // LinkedList implementation provided by LeetCode.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        this.removeNth(head, n);

        // If the list has not been touched yet, it means the nth node is the head, let's drop it.
        if (!this.removed) {
            head = head.next;
        }

        return head;
    }

    private ListNode removeNth(ListNode head, int n) {
        if (head.next == null) {
            this.gradeFromEnd = n - 1;
            return head;
        }

        ListNode current = removeNth(head.next, n);

        if (this.gradeFromEnd == 0) {
            head.next = current.next;
            this.removed = true;
        }

        this.gradeFromEnd --;
        return head;
    }
}

