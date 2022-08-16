/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 05/08/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/container-with-most-water/
public class MaxArea {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;


        while (i < j) {
            int area = this.getArea(i, j, height);

            if (height[i] < height[j]) {
                i++;
            } else {
                j --;
            }

            if (area > max) {
                max = area;
            }
        }

        return max;
    }

    private int getArea(int i, int j, int[] height) {
        return Math.abs(i-j) * Math.min(height[i], height[j]);
    }
}
