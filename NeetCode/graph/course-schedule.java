class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] indegree = new int[numCourses];
    Map<Integer, List<Integer>> childCourses = new HashMap<>();
    for (int[] pair : prerequisites) {
      int before = pair[1], after = pair[0];
      indegree[after]++;
      if (!childCourses.containsKey(before)) {
        childCourses.put(before, new ArrayList<Integer>());
      }
      childCourses.get(before).add(after);
    }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
      }
    }
    int completed = q.size();
    while (!q.isEmpty()) {
      int curr = q.poll();
      if (!childCourses.containsKey(curr)) continue;
      List<Integer> children = childCourses.get(curr);
      for (int child : children) {
        indegree[child]--;
        if (indegree[child] == 0) {
          q.offer(child);
          completed++;
        }
      }
    }
    return completed == numCourses;
  }
}
