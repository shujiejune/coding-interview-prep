class DSU {
  private int[] parent;
  private int[] size;

  public DSU(int n) {
    parent = new int[n + 1];
    size = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int i) {
    if (parent[i] != i) {
      parent[i] = find(parent[i]);
    }
    return parent[i];
  }

  public boolean union(int u, int v) {
    int pu = parent[u];
    int pv = parent[v];
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
  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  public int maxAreaOfIsland(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    DSU dsu = new DSU(m * n);
    int maxArea = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          for (int[] d : directions) {
            int x = i + d[0];
            int y = i + d[1];
            if (isValid(m, n, x, y) && grid[x][y] == 1) {
              dsu.union(i * n + j, x * n + y);
            }
          }
          maxArea = Math.max(maxArea, dsu.size[dsu.parent[i * n + j]]);
        }
      }
    }
    return maxArea;
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
// TC: O(mn)
// SC: O(mn)
