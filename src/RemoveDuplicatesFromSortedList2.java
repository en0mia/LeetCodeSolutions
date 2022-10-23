/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 23/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveDuplicatesFromSortedList2 {
    // Implementation provided by the problem
    private class ListNode {
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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode sentinel = new ListNode(0, head);
        ListNode attachPoint = sentinel;

        ListNode current = head;

        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                    attachPoint.next = current.next;
                }
            } else {
                attachPoint = current;
            }

            current = current.next;
        }

        return sentinel.next;
    }
}
