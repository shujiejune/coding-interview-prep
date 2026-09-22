class Solution {
  private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length, n = heights[0].length;
    boolean[][] pacific = new boolean[m][n];
    boolean[][] atlantic = new boolean[m][n];
    Queue<int[]> p = new LinkedList<>();
    Queue<int[]> a = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      p.offer(new int[]{i, 0});
      a.offer(new int[]{i, n - 1});
      pacific[i][0] = true;
      atlantic[i][n - 1] = true;
    }
    for (int j = 0; j < n; j++) {
      p.offer(new int[]{0, j});
      a.offer(new int[]{m - 1, j});
      pacific[0][j] = true;
      atlantic[m - 1][j] = true;
    }
    bfs(heights, p, pacific);
    bfs(heights, a, atlantic);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          ans.add(Arrays.asList(new Integer[]{i, j}));
        }
      }
    }
    return ans;
  }

  private void bfs(int[][] heights, Queue<int[]> q, boolean[][] ocean) {
    int m = heights.length, n = heights[0].length;
    boolean[][] visited = new boolean[m][n];
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      for (int[] d : directions) {
        int x = curr[0] + d[0];
        int y = curr[1] + d[1];
        if (isValid(m, n, x, y) && !visited[x][y]) {
          if (heights[x][y] >= heights[curr[0]][curr[1]]) {
            q.offer(new int[]{x, y});
            ocean[x][y] = true;
            visited[x][y] = true;
          }
        }
      }
    }
  }

  private boolean isValid(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
