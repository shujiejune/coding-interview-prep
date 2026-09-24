class Solution {
  public static long minimumMoney(int[] quality) {
    int n = quality.length;
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, Integer> lastOccurrence = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int val = quality[i];
      freq.put(val, freq.getOrDefault(val, 0) + 1);
      lastOccurrence.put(val, i);
    }
    int ans = 0;
    int start = 0, right = 0;
    int maxFreq = 0;
    for (int i = 0; i < n; i++) {
      int val = quality[i];
      right = Math.max(right, lastOccurrence.get(val));
      maxFreq = Math.max(maxFreq, freq.get(val));
      if (i == right) {
        int segmentLen = i - start + 1;
        ans += segmentLen - maxFreq;
        start = i + 1;
        maxFreq = 0;
      }
    }
    return ans;
  }
}
