class Solution {
  private static int rows;
  private static int cols;
  private static int[][] grid;
  private static int[][] dist;
  private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public static int minimumInconvenience(int[][] input) {
    grid = input;
    rows = grid.length;
    cols = grid[0].length;
    boolean hasZero = false;
    for (int[] row : grid) {
      for (int val : row) {
        if (val == 0) {
          hasZero = true;
          break;
        }
      }
    }
    if (!hasZero) return 0;

    buildDistances();

    int low = 0;
    int high = rows + cols - 2;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (canAchieve(mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private static void buildDistances() {
    dist = new int[rows][cols];
    for (int[] row : dist) {
      Arrays.fill(row, -1);
    }
    Queue<int[]> q = new LinkedList<>();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == 1) {
          dist[r][c] = 0;
          q.offer(new int[]{r, c});
        }
      }
    }
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int r = curr[0];
      int c = curr[1];
      for (int[] d : directions) {
        int x = r + d[0];
        int y = c + d[1];
        if (!isValid(x, y) || dist[x][y] != -1) continue;
        dist[x][y] = dist[r][c] + 1;
        q.offer(new int[]{x, y});
      }
    }
  }

  private static boolean canAchieve(int d) {
    int minSum = Integer.MAX_VALUE;
    int maxSum = Integer.MIN_VALUE;
    int minDiff = Integer.MAX_VALUE;
    int maxDiff = Integer.MIN_VALUE;
    boolean hasBadCell = false;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (dist[r][c] == -1 || dist[r][c] > d) {
          hasBadCell = true;
          int sum = r + c;
          int diff = r - c;
          minSum = Math.min(minSum, sum);
          maxSum = Math.max(maxSum, sum);
          minDiff = Math.min(minDiff, diff);
          maxDiff = Math.max(maxDiff, diff);
        }
      }
    }

    if (!hasBadCell) return true;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int sum = r + c;
        int diff = r - c;
        int farthest = Math.max(
          Math.max(maxSum - sum, sum - minSum),
          Math.max(maxDiff - diff, diff - minDiff)
        );
        if (farthest <= d) return true;
      }
    }
    return false;
  }

  private static boolean isValid(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
  }
}
