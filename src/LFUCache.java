/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 23/10/22
    @copyright: Check the repository license.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Problem link: https://leetcode.com/problems/lfu-cache/
public class LFUCache {
    private final int capacity;
    private final TreeMap<Integer, DoublyLinkedList> frequencies = new TreeMap<>();
    private final Map<Integer, Integer> keyFrequency = new HashMap<>();
    private final Map<Integer, Node> values = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }

        final Node node = values.get(key);
        final int frequency = keyFrequency.get(key);

        updateFrequency(key, frequency, frequency + 1);

        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (!values.containsKey(key)) {
            final Node node = new Node(key, value);

            if (values.size() == capacity) {
                resize();
            }

            // Insert the new node
            frequencies.computeIfAbsent(1, k -> new DoublyLinkedList());
            frequencies.get(1).insert(node);
            values.put(key, node);
            keyFrequency.put(key, 1);
        } else {
            final Node node = values.get(key);

            // Update the value
            node.val = value;

            final int frequency = keyFrequency.get(key);
            updateFrequency(key, frequency, frequency + 1);
        }
    }

    private void resize() {
        // Remove least used element with least frequency
        final int lowestFreq = frequencies.firstKey();
        final DoublyLinkedList list = frequencies.get(lowestFreq);
        final Node leastUsed = list.getFirst();
        list.remove(leastUsed);
        values.remove(leastUsed.key);
        keyFrequency.remove(leastUsed.key);

        if (frequencies.get(lowestFreq).isEmpty()) {
            frequencies.remove(lowestFreq);
        }
    }

    private void updateFrequency(final int key, final int oldFrequency, final int newFrequency) {
        final Node node = values.get(key);
        final DoublyLinkedList valuesList = frequencies.get(oldFrequency);

        // Remove the node from the old frequency
        valuesList.remove(node);

        if (frequencies.get(oldFrequency).isEmpty()) {
            frequencies.remove(oldFrequency);
        }

        frequencies.computeIfAbsent(newFrequency, k -> new DoublyLinkedList());

        // Insert the node
        final Node newNode = new Node(node.key, node.val);
        frequencies.get(newFrequency).insert(newNode);
        keyFrequency.put(key, newFrequency);
        values.put(key, newNode);
    }

    private static class Node {
        public int key, val;
        public Node previous, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // Auxiliary classes.
    private static class DoublyLinkedList {
        public Node head, tail;
        public int n = 0;
        public Node getFirst() {
            return head;
        }

        // Insert at the end
        public void insert(final Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.previous = tail;
            }

            tail = node;
            n ++;
        }

        public void remove (final Node node) {
            if (node.next == null) {
                tail = node.previous;
            } else {
                node.next.previous = node.previous;
            }

            if (node.previous == null) {
                head = node.next;
            } else {
                node.previous.next = node.next;
            }

            n --;
        }

        public boolean isEmpty() {
            return n == 0;
        }
    }
}
