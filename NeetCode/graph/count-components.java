class DSU {
  int[] parent, size;

  public DSU(int n) {
    parent = new int[n + 1];
    size = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int node) {
    if (parent[node] != node) {
      parent[node] = find(parent[node]);
    }
    return parent[node];
  }

  public boolean union(int u, int v) {
    int pu = find(u);
    int pv = find(v);
    if (pu == pv) return false;
    if (size[pu] >= size[pv]) {
      size[pu] += size[pv];
      parent[pv] = pu;
    } else {
      size[pv] += size[pu];
      parent[pu] = pv;
    }
    return true;
  }
}

class Solution {
  public int countComponents(int n, int[][] edges) {
    DSU dsu = new DSU(n);
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      dsu.union(u, v);
    }
    Set<Integer> roots = new HashSet<>();
    for (int i = 0; i < n; i++) {
      roots.add(dsu.find(i));
    }
    return roots.size();
  }
}
