class Solution {
  public int[] findRedundantConnection(int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    int n = edges.length;
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      adj.get(u).add(v);
      adj.get(v).add(u);
      boolean[] visited = new boolean[n + 1];
      if (dfs(u, -1, adj, visited)) {
        return edge;
      }
    }
  }

  private boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] visited) {
    if (visited[node]) {
      return true;
    }
    visited[node] = true;
    for (int nei : adj.get(i)) {
      if (nei == parent) continue;
      if (dfs(nei, node, adj, visited)) return true;
    }
    return false;
  }
}
// TC: O(E * (V + E))
// SC: O(V + E)

// DFS Optimal
class Solution {
  private boolean[] visited;
  private List<List<Integer>> adj;
  private Set<Integer> cycle;
  private int cycleStart;

  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    visited = new boolean[n + 1];
    cycle = new HashSet<>();
    cycleStart = -1;
    dfs(1, -1);
    for (int i = n - 1; i >= 0; i--) {
      int u = edges[i][0],v = edges[i][1];
      if (cycle.contains(u) && cycle.contains(v)) {
        return new int[]{u, v};
      }
    }
    return new int[0];
  }

  private boolean dfs(int node, int parent) {
    if (visited[node]) {
      cycleStart = node;
      return true;
    }
    visited[node] = true;
    for (int nei : adj.get(node)) {
      if (nei == parent) continue;
      if (dfs(nei, node)) {
        if (cycleStart != -1) {
          cycle.add(node);
        }
        return true;
      }
    }
    return false;
  }
}
// TC: O(V + E)
// SC: O(V + E)

// Topological Sort (Kahn's Algorithm)
// Even though the graph is undirected, we can use the "peel off leaves" idea.
// Nodes with degree 1 cannot be inside a cycle.
// When we remove leaf nodes, its neighbor's degree decreases.
// After this process finishes, the only nodes left with degree > 0 are in the cycle.
class Solution {
  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    int[] indegree = new int[n + 1];
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      adj.get(u).add(v);
      adj.get(v).add(u);
      indegree[u]++;
      indegree[v]++;
    }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
      if (indegree[i] == 1) {
        q.offer(i);
      }
    }
    while (!q.isEmpty()) {
      int curr = q.poll();
      indegree[curr]--;
      for (int nei : adj.get(curr)) {
        indegree[nei]--;
        if (indegree[nei] == 1) {
          q.offer(nei);
        }
      }
    }
    for (int i = n - 1; i >= 0; i--) {
      int u = edges[i][0], v = edges[i][1];
      if (indegree[u] == 2 && indegree[v] == 2) {
        return new int[]{u, v};
      }
    }
    return new int[0];
  }
}
// TC: O(V + E)
// SC: O(V + E)
