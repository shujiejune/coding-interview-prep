/* Given an array A of N integers. You can split the array into 
 * 2 non-empty parts, left and right, sort the elements in each 
 * part independently and join them back together.
 * Your task is to find the number of ways of splitting the array
 * into 2 parts such that, after sorting the 2 parts and rejoining 
 * them into a single array, the resulting array will be sorted
 * in non-decreasing order.
 */
public int sortedSplit(int[] array) {
	if (array == null || array.length <= 1) {
		return 0;
	}
	int n = array.length;
	int[] leftMax = new int[n];
	int[] rightMin = new int[n];
	int currMax = Integer.MIN_VALUE, currMin = Integer.MAX_VALUE;
	for (int i = 0; i < n; i++) {
		currMax = Math.max(currMax, array[i]);
		leftMax[i] = currMax;
	}
	for (int i = n - 1; i >= 0; i--) {
		currMin = Math.min(currMin, array[i]);
		rightMin[i] = currMin;
	}
	int count = 0;
	for (int i = 0; i < n - 1; i++) {
		if (leftMax[i] <= rightMin[i + 1]) {
			count++;
		}
	}
	return count;
}
