/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 16/08/22
    @copyright: Check the repository license.
*/

import java.util.HashMap;
import java.util.Map;

// Problem link: https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up
public class MinimumConsecutiveCardsToPickUp {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> lastOccurrenceIndex = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            if (lastOccurrenceIndex.containsKey(cards[i])) {
                min = Math.min(min, i - lastOccurrenceIndex.get(cards[i]) + 1);
            }

            lastOccurrenceIndex.put(cards[i], i);
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }
}
