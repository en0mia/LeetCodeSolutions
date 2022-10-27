/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 27/10/22
    @copyright: Check the repository license.
*/

import java.util.LinkedHashMap;

// Problem link: https://leetcode.com/problems/lru-cache/
public class LRUCache {
    private final int n;
    private final LinkedHashMap<Integer, Integer> values;

    public LRUCache(int capacity) {
        n = capacity;
        values = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }

        reinsert(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (values.containsKey(key)) {
            reinsert(key);
            return;
        }

        if (values.size() == n) {
            // Remove the first (least used) key to resize the cache
            values.remove(values.keySet().iterator().next());
        }

        values.put(key, value);
    }

    private void reinsert(int key) {
        final int value = values.get(key);
        values.remove(key);
        values.put(key, value);
    }
}
