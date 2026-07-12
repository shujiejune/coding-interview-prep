/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 * For example:

 * 2, [[1,0]]

 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

 * 2, [[1,0],[0,1]]

 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

 * Note:

 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Write your solution here
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] indegree = new int[numCourses];
    for (int[] p : prerequisites) {
      int course = p[0];
      int pre = p[1];
      indegree[course]++;
      if (graph.containsKey(pre)) {
        graph.get(pre).add(course);
      } else {
        List<Integer> kids = new ArrayList<>();
        kids.add(course);
        graph.put(pre, kids);
      }
    }
    Queue<Integer> q = new LinkedList<>();
    int count = 0;
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
        count++;
      }
    }
    while (!q.isEmpty()) {
      int curr = q.poll();
      if (!graph.containsKey(curr)) {
        continue;
      }
      List<Integer> kids = graph.get(curr);
      for (int k : kids) {
        indegree[k]--;
        if (indegree[k] == 0) {
          q.offer(k);
          count++;
        }
      }
    }
    if (count == numCourses) {
      return true;
    } else {
      return false;
    }
  }
}
