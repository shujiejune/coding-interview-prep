class Solution {
  public boolean validTree(int n, int[][] edges) {
    if (edges.length != n - 1) return false;
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    Set<Integer> visited = new HashSet<>();
    Queue<int[]> q = new LinkedList<>();
    visited.add(0);
    q.offer(new int[]{0, -1}); // {current, parent}
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int node = curr[0], parent = curr[1];
      for (int nei : adj.get(node)) {
        if (nei == parent) continue;
        if (visited.contains(nei)) return false;
        visited.add(nei);
        q.offer(new int[]{nei, node});
      }
    }
    return visited.size() == n;
  }
}
