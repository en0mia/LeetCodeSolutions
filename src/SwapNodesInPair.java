/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 21/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesInPair {
    // Implementation given by the problem
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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode next = head.next;
        ListNode before = head;

        // Keep the head updated, this is needed only at the first swap.
        head = next;

        while (next != null) {
            before.next = next;
            before = current;

            current.next = next.next;
            next.next = current;

            if (current.next == null || current.next.next == null) {
                break;
            }

            current = current.next;
            next = current.next;
        }

        return head;
    }
}
