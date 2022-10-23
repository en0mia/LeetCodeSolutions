/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 23/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class RemoveDuplicatesFromSortedList {
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

        ListNode previous = head;
        ListNode current = previous.next;

        while (current != null) {
            if (current.val == previous.val) {
                // it is a duplicate: drop it
                previous.next = current.next;
            } else {
                // Previous should proceed only if we drop an element
                previous = current;
            }

            // Current should always proceed
            current = current.next;
        }

        return head;
    }
}
