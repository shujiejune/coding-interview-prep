class Solution {
  private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public void solve(char[][] board) {
    int m = board.length, n = board[0].length;
    Queue<int[]> q = new LinkedList<>();
    boolean[][] marked = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') {
        q.offer(new int[]{i, 0});
        marked[i][0] = true;
      }
      if (board[i][n - 1] == 'O') {
        q.offer(new int[]{i, n - 1});
        marked[i][n - 1] = true;
      }
    }
    for (int j = 0; j < n; j++) {
      if (board[0][j] == 'O') {
        q.offer(new int[]{0, j});
        marked[0][j] = true;
      }
      if (board[m - 1][j] == 'O') {
        q.offer(new int[]{m - 1, j});
        marked[m - 1][j] = true;
      }
    }
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      for (int[] d : directions) {
        int x = curr[0] + d[0];
        int y = curr[1] + d[1];
        if (isValid(m, n, x, y) && board[x][y] == 'O' && !marked[x][y]) {
          marked[x][y] = true;
          q.offer(new int[]{x, y});
        }
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!marked[i][j]) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}

// Use Union-Find to group connected 'O' cells
// Use a dummy node to represent "connected to border"
// - Union every border 'O' with the dummy node
// - Union every 'O' with its neighboring 'O' cells
// - Any cell not connected to the dummy node is surrounded
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

  public boolean connected(int u, int v) {
    return find(u) == find(v);
  }
}

public class Solution {
  public void solve(char[][] board) {
    int m = board.length, n = board[0].length;
    DSU dsu = new DSU(m * n + 1);
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] != 'O') continue;
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
          dsu.union(m * n, i * n + j);
        } else {
          for (int[] d : directions) {
            int x = i + d[0];
            int y = j + d[1];
            if (board[x][y] == 'O') {
              dsu.union(i * n + j, x * n + y);
            }
          }
        }
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!dsu.connected(m * n, i * n + j)) {
          board[i][j] = 'X';
        }
      }
    }
  }
}
