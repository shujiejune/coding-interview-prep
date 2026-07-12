/* Given an array, find the subarray that has the largest sum.
 * Return sum.
 */
public int largestSum(int[] array) {
	if (array == null || array.length == 0) {
		return 0;
	}
	int n = array.length;
	int smallestPrefixSum = 0;
	int[] prefixSum = new int[n];
	prefixSum[0] = array[0];
	int globalMax = array[0];
	for (int i = 1; i < n; i++) {
		prefixSum[i] = prefixSum[i - 1] + array[i];
		smallestPrefixSum = Math.min(smallestPrefixSum, prefixSum[i - 1]);
		globalMax = Math.max(globalMax, prefixSum[i] - smallestPrefixSum);
	}
	return globalMax;
}
