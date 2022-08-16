/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 16/08/22
    @copyright: Check the repository license.
*/

import java.util.ArrayList;
import java.util.List;

// Problem link: https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers {
    // Provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static class SumPair {
        int sum;
        int carry;

        public SumPair(int sum, int carry) {
            this.sum = sum;
            this.carry = carry;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        final List<Integer> list1 = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();
        ListNode result = new ListNode();
        ListNode head = result;
        int carry = 0;

        while (l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }

        int i = 0, j = 0, k = 0;
        int max = Math.max(list1.size(), list2.size());
        SumPair sumPair;

        while (k < max) {
            int a = (i >= list1.size() ? 0 : list1.get(i));
            int b = (j >= list2.size() ? 0 : list2.get(j));

            sumPair = sum(a, b, carry);
            carry = sumPair.carry;
            result.next = new ListNode(sumPair.sum);
            result = result.next;
            i++;
            j++;
            k++;
        }

        if (carry != 0) {
            result.next = new ListNode(carry);
        }

        return head.next;
    }

    private SumPair sum(int a, int b, int carry) {
        int sum = a + b + carry;

        if (sum > 9) {
            return new SumPair(sum - 10, 1);
        }

        return new SumPair(sum, 0);
    }
}
