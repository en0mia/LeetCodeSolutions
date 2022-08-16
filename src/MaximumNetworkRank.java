/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 30/07/22
    @copyright: Check the repository license.
*/

import java.util.*;

// Problem link: https://leetcode.com/problems/maximal-network-rank/
public class MaximumNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> connected = new HashSet<>();

        for (int[] i : roads) {
            int old = map.getOrDefault(i[0], 0);
            map.put(i[0], old + 1);
            old = map.getOrDefault(i[1], 0);
            map.put(i[1], old + 1);
            connected.add(String.format("%d%d", i[0], i[1]));
            connected.add(String.format("%d%d", i[1], i[0]));
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int res = map.getOrDefault(i, 0) + map.getOrDefault(j, 0);
                if (connected.contains(String.format("%d%d", i, j))) {
                    res --;
                }
                if (res > max) {
                    max = res;
                }
            }
        }

        return max;
    }
}
