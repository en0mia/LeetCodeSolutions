/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 05/08/22
    @copyright: Check the repository license.
*/

import java.util.Arrays;

// Problem link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, 0, nums.length - 1, target);

        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findLast(nums, 0, nums.length - 1, target);

        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int left, int right, int goal) {
        int res = -1;
        while (left <= right) {
            int m = left + (right - left) / 2;

            if (nums[m] == goal) {
                res = m;
                right = m - 1;
            } else if (nums[m] > goal) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }

        return res;
    }

    private int findLast(int[] nums, int left, int right, int goal) {
        int res = -1;
        while (left <= right) {
            int m = left + (right - left) / 2;

            if (nums[m] == goal) {
                res = m;
                left = m + 1;
            } else if (nums[m] > goal) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }

        return res;
    }
}
