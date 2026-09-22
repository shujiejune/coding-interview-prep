class Solution {
  public int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    int totalFresh = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          q.offer(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          totalFresh++;
        }
      }
    }
    if (q.size() == 0) return totalFresh == 0 ? 0 : -1;
    int elapse = 0;
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    while (!q.isEmpty()) {
      int num = q.size();
      boolean change = false;
      for (int i = 0; i < num; i++) {
        int[] curr = q.poll();
        for (int[] d : directions) {
          int x = curr[0] + d[0];
          int y = curr[1] + d[1];
          if (isValid(m, n, x, y) && grid[x][y] == 1) {
            totalFresh--;
            grid[x][y] = 2;
            q.offer(new int[]{x, y});
            change = true;
          }
        }
      }
      if (change) elapse++;
    }
    return totalFresh == 0 ? elapse : -1;
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
// TC: O(mn)
// SC: O(mn)
