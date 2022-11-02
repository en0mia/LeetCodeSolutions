/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 02/11/22
    @copyright: Check the repository license.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Problem link: https://leetcode.com/problems/clone-graph/
public class CloneGraph {
    // Implementation provided by the problem
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        // Edge case; empty graph
        if (node == null) {
            return null;
        }

        Map<Integer, Node> map = new HashMap<>();
        // Launch the search from the current node
        cloneGraphRecursive(map, node);
        return map.get(node.val);
    }

    public void cloneGraphRecursive(Map<Integer, Node> map, Node node) {
        // We already visited the node
        if (map.containsKey(node.val)) {
            return;
        }

        // Clone the node
        map.put(node.val, new Node(node.val));

        for (Node n : node.neighbors) {
            cloneGraphRecursive(map, n);

            // Add the neighbor with all its neighbors to the current node's neighbors
            map.get(node.val).neighbors.add(map.get(n.val));
        }
    }
}
