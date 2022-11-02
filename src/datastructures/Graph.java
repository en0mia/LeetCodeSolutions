/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 02/11/22
    @copyright: Check the repository license.
*/

package datastructures;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private static class Vertex {
        int value;

        Vertex(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            return value == vertex.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }

    private Map<Vertex, List<Vertex>> adjacency;

    public void addVertext(int value) {
        adjacency.putIfAbsent(new Vertex(value), new ArrayList<>());
    }

    public void removeVertex(int value) {
        final Vertex v = new Vertex(value);
        // Remove when v is a neighbor
        adjacency.values().stream().forEach(l -> l.remove(v));
        // Remove the key
        adjacency.remove(v);
    }

    public void addEdge(int v1, int v2) {
        final Vertex vertex1 = new Vertex(v1);
        final Vertex vertex2 = new Vertex(v2);

        adjacency.get(vertex1).add(vertex2);
        adjacency.get(vertex2).add(vertex1);
    }

    public void removeEdge(int v1, int v2) {
        final Vertex vertex1 = new Vertex(v1);
        final Vertex vertex2 = new Vertex(v2);

        if (adjacency.get(vertex1) != null) {
            adjacency.get(vertex1).remove(vertex2);
        }

        if (adjacency.get(vertex2) != null) {
            adjacency.get(vertex2).remove(vertex1);
        }
    }

    public List<Vertex> getAdj(int value) {
        return adjacency.get(new Vertex(value));
    }

    // Depth-First-Search
    public static Set<Integer> dfs(Graph graph, int root) {
        final Set<Integer> visited = new LinkedHashSet<>();
        final Stack<Integer> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : graph.getAdj(vertex)) {
                    stack.push(v.value);
                }
            }
        }

        return visited;
    }

    // Breadth-First-Search
    public static Set<Integer> bfs(Graph graph, int root) {
        final Set<Integer> visited = new LinkedHashSet<>();
        final Queue<Integer> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (Vertex v : graph.getAdj(vertex)) {
                if (!visited.contains(v.value)) {
                    visited.add(v.value);
                    queue.add(v.value);
                }
            }
        }

        return visited;
    }
}
