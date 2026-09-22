class Solution {
  private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public void islandsAndTreasure(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] > 0) {
          boolean[][] visited = new boolean[m][n];
          bfs(grid, i, j, visited);
        }
      }
    }
  }

  private void bfs(int[][] grid, int i, int j, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{i, j});
    visited[i][j] = true;
    int dist = 0;
    while (!q.isEmpty()) {
      dist++;
      List<int[]> level = new ArrayList<>();
      while (!q.isEmpty()) {
        level.add(q.poll());
      }
      for (int[] curr : level) {
        for (int[] d : directions) {
          int x = curr[0] + d[0];
          int y = curr[1] + d[1];
          if (isValid(m, n, x, y) && !visited[x][y]) {
            if (grid[x][y] > 0) {
              q.offer(new int[]{x, y});
              visited[x][y] = true;
            } else if (grid[x][y] == 0) {
              grid[i][j] = dist;
              return;
            }
          }
        }
      }
    }
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
// TC: O(m^2 * n^2)
// SC: O(mn)

// Multi-source BFS
// Run BFS once from all reasure cells at the same time
public class Solution {
  public void islandsAndTreasure(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          q.offer(new int[]{i, j});
        }
      }
    }
    if (q.size() == 0) return;
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      for (int[] d : directions) {
        int x = curr[0] + d[0];
        int y = curr[1] + d[1];
        if (isValid(m, n, x, y) && grid[x][y] > grid[curr[0]][curr[1]] + 1) {
          grid[x][y] = grid[curr[0]][curr[1]] + 1;
          q.offer(new int[]{x, y});
        }
      }
    }
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
