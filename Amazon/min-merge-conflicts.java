class Solution {
  public static long minimumConflicts(String primary, String secondary) {
    int m = primary.length(), n = secondary.length();
    int[][] greaterPrimary = buildGreaterPrefix(primary);
    int[][] greaterSecondary = buildGreaterPrefix(secondary);
    long fixedConflicts = countInternalInversions(primary) + countInternalInversions(secondary);
    long[][] dp = new long[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      dp[i][0] = 0;
    }
    for (int j = 1; j <= n; j++) {
      dp[0][j] = 0;
    }
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        char p = primary.charAt(i - 1);
        char s = secondary.charAt(j - 1);
        long appendPrimary = dp[i - 1][j] + greaterSecondary[j][p - 'a'];
        long appendSecondary = dp[i][j - 1] + greaterPrimary[i][s - 'a'];
        dp[i][j] = Math.min(appendPrimary, appendSecondary);
      }
    }
    return fixedConflicts + dp[m][n];
  }

  private static int[][] buildGreaterPrefix(String s) {
    int n = s.length();
    int[][] freq = new int[n + 1][26];
    for (int i = 1; i <= n; i++) {
      System.arraycopy(freq[i - 1], 0, freq[i], 0, 26);
      int c = s.charAt(i - 1) - 'a';
      freq[i][c]++;
    }
    int[][] greater = new int[n + 1][26];
    for (int i = 0; i <= n; i++) {
      int running = 0;
      for (int c = 25; c >= 0; c--) {
        greater[i][c] = running;
        running += freq[i][c];
      }
    }
    return greater;
  }

  private static long countInternalInversions(String s) {
    int[] freq = new int[26];
    long inversion = 0;
    for (char ch : s.toCharArray()) {
      int c = ch - 'a';
      for (int x = c + 1; x < 26; x++) {
        inversions += freq[x];
      }
      freq[c]++;
    }
    return inversions;
  }
}
