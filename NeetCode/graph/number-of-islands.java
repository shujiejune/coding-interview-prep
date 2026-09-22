class Solution {
  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] visited = new int[m][n];
    int[] num = {1};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1' && visited[i][j] == 0) {
          visited[i][j] = num[0];
          bfs(grid, i, j, num, visited);
        }
      }
    }
    return num[0] - 1;
  }

  private void bfs(char[][] grid, int i, int j, int[] num, int[][] visited) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{i, j});
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      for (int[] d : directions) {
        int x = curr[0] + d[0];
        int y = curr[1] + d[1];
        if (isValid(m, n, x, y) && grid[x][y] == '1' && visited[x][y] == 0) {
          q.offer(new int[]{x, y});
          visited[x][y] = num[0];
        }
      }
    }
    num[0]++;
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
// TC: O(mn)
// SC: O(mn)

// Union Find
// parent[i]: The index of the direct ancestor of node i in the forest.
// initial state: parent[i] = i. Every node starts as its own independent set.
// A node is the root of its set iff parent[i] = i.
// size[i]: The total count of nodes currently in the tree rooted at node i.
// size is only physically meaningful and valid at the root node.
class DSU {
  private int[] parent, size;

  public DSU(int n) {
    parent = new int[n + 1];
    size = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int node) {
    if (node != parent[node]) {
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
  public int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    DSU dsu = new DSU(m * n);
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int islands = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          islands++;
          for (int[] d : directions) {
            int x = i + d[0];
            int y = j + d[1];
            if (isValid(m, n, x, y) && grid[x][y] == '1') {
              if (dsu.union(i * n + j, x * n + y)) {
                islands--;
              }
            }
          }
        }
      }
    }
    return islands;
  }
}
// TC: O(mn)
// SC: O(mn)
