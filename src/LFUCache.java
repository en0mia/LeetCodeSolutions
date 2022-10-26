/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 23/10/22
    @copyright: Check the repository license.
*/

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

// Problem link: https://leetcode.com/problems/lfu-cache/
public class LFUCache {
    private final int capacity;
    private final TreeMap<Integer, LinkedHashSet<Integer>> frequencies = new TreeMap<>();
    private final Map<Integer, Integer> keyFrequency = new HashMap<>();
    private final Map<Integer, Integer> values = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }

        final int frequency = keyFrequency.get(key);

        updateFrequency(key, frequency, frequency + 1);

        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (!values.containsKey(key)) {
            if (values.size() == capacity) {
                resize();
            }

            // Insert the new node
            frequencies.computeIfAbsent(1, k -> new LinkedHashSet<>());
            frequencies.get(1).add(key);
            values.put(key, value);
            keyFrequency.put(key, 1);
        } else {
            values.put(key, value);
            final int frequency = keyFrequency.get(key);
            updateFrequency(key, frequency, frequency + 1);
        }
    }

    private void resize() {
        // Remove least used element with least frequency
        final int lowestFreq = frequencies.firstKey();
        final LinkedHashSet<Integer> list = frequencies.get(lowestFreq);
        final int leastUsed = list.iterator().next();
        list.remove(leastUsed);
        values.remove(leastUsed);
        keyFrequency.remove(leastUsed);

        if (frequencies.get(lowestFreq).isEmpty()) {
            frequencies.remove(lowestFreq);
        }
    }

    private void updateFrequency(final int key, final int oldFrequency, final int newFrequency) {
        final LinkedHashSet<Integer> valuesList = frequencies.get(oldFrequency);
        final int value = values.get(key);

        // Remove the node from the old frequency
        valuesList.remove(key);

        if (frequencies.get(oldFrequency).isEmpty()) {
            frequencies.remove(oldFrequency);
        }

        frequencies.computeIfAbsent(newFrequency, k -> new LinkedHashSet<>());

        // Insert the node
        frequencies.get(newFrequency).add(key);
        keyFrequency.put(key, newFrequency);
        values.put(key, value);
    }
}
