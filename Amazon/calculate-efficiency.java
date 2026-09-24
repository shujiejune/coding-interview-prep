class Solution {
  public static long calculateEfficiency(List<Integer> arr, List<List<Integer>> pairs) {
    int n = arr.size();
    // Step 1 - Sweep Line: count how many times each index is selected in pairs.
    long[] diff = new long[n + 1];
    for (List<Integer> pair : pairs) {
      int start = pair.get(0), end = pair.get(1);
      diff[start]++;
      diff[end + 1]--;
    }
    long[] count = new long[n];
    long active = 0;
    for (int i = 0; i < n; i++) {
      active += diff[i];
      count[i] = active;
    }

    // Step 2 - Prefix Sum: sort indices by corresponding values in arr.
    Integer[] indices = new Integer[n];
    for (int i = 0; i < n; i++) {
      indices[i] = i;
    }
    Arrays.sort(indices, Comparator.comparingInt(arr:get));
    int[] sortedValues = new int[n];
    long[] prefix = new long[n + 1];
    for (int i = 0; i < n; i++) {
      int index = indices[i];
      sortedValues[i] = arr.get(index);
      prefix[i + 1] = prefix[i] + count[index];
    }

    // Step 3 - Binary Search: calculate efficiency for unselected indices.
    long ans = 0;
    for (int i = 0; i < n; i++) {
      if (count[i] > 0) continue;
      int val = arr.get(i);
      int pos = lowerBound(sortedValues, val);
      ans += prefix[pos];
    }
    return ans;
  }

  private static int lowerBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
}
