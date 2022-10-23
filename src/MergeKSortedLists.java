/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 23/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedLists {
    // Implementation provided by the problem
    public class ListNode {
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

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        // Keep a reference to the last element, so we don't have to loop over the list each time.
        ListNode lastResultNode = null;
        int nulls = 0;

        while (nulls < lists.length) {
            // Find the minimum to append
            ListNode min = null;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode list = lists[i];

                if (list == null) {
                    continue;
                }

                if (min == null || list.val < min.val) {
                    min = list;
                    minIndex = i;
                }
            }

            if (min == null) {
                break;
            }

            // Append
            if (result == null) {
                result = new ListNode(min.val);
                lastResultNode = result;
            } else {
                lastResultNode.next = new ListNode(min.val);
                lastResultNode = lastResultNode.next;
            }

            // Delete the appended value
            lists[minIndex] = lists[minIndex].next;

            // nulls is used as the end condition.
            if (lists[minIndex] == null) {
                nulls ++;
            }
        }
        return result;
    }
}
