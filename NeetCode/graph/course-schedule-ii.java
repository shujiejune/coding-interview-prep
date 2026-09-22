class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] indegree = new int[numCourses];
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] pair : prerequisites) {
      int before = pair[1], after = pair[0];
      indegree[after]++;
      adj.get(before).add(after);
    }
    List<Integer> order = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
        order.add(i);
      }
    }
    while (!q.isEmpty()) {
      int curr = q.poll();
      List<Integer> children = adj.get(curr);
      for (int child : children) {
        indegree[child]--;
        if (indegree[child] == 0) {
          q.offer(child);
          order.add(child);
        }
      }
    }
    if (order.size() == numCourses) {
      int[] ans = new int[numCourses];
      for (int i = 0; i < numCourses; i++) {
        ans[i] = order.get(i);
      }
      return ans;
    }
    return new int[0];
  }
}
