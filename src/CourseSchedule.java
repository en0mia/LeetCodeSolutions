/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 02/11/22
    @copyright: Check the repository license.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) {
            return true;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] couple : prerequisites) {
            graph.putIfAbsent(couple[0], new ArrayList<>());
            graph.putIfAbsent(couple[1], new ArrayList<>());

            graph.get(couple[0]).add(couple[1]);
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> rec = new HashSet<>();

        for (Integer v : graph.keySet()) {
            if (hasCycles(graph, v, visited, rec)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasCycles(Map<Integer, List<Integer>> graph, int root, Set<Integer> visited, Set<Integer> rec) {
        if (rec.contains(root)) {
            return true;
        }

        if (visited.contains(root)) {
            return false;
        }

        visited.add(root);
        rec.add(root);

        for (Integer v : graph.get(root)) {
            if (hasCycles(graph, v, visited, rec)) {
                return true;
            }
        }

        rec.remove(root);
        return false;
    }
}
