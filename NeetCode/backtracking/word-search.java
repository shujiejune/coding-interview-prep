class Solution {
  boolean ans = false;
  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == word.charAt(0)) {
          visited[i][j] = true;
          dfs(board, m, n, i, j, 1, word, visited);
          visited[i][j] = false;
        }
      }
    }
    return ans;
  }

  private void dfs(char[][] board, int m, int n, int i, int j, int index, String word, boolean[][] visited) {
    if (index == word.length()) {
      ans = true;
      return;
    }
    for (int[] d : directions) {
      int x = i + d[0];
      int y = j + d[1];
      if (isValid(m, n, x, y) && !visited[x][y]) {
        char c = board[x][y];
        if (c == word.charAt(index)) {
          visited[x][y] = true;
          dfs(board, m, n, x, y, index + 1, word, visited);
          visited[x][y] = false;
        }
      }
    }
  }

  private boolean isValid(int m, int n, int i, int j) {
    return i >= 0 && i < m && j >= 0 && j < n;
  }
}
