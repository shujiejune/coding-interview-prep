/* Given an array A of length N containing all positive integers 
 * from [1...N]. How to get an array B such that B[i] represents 
 * how many elements A[j] (j > i) in array A that are smaller than A[i].
 * Assumptions: The given array A is not null.
 * Requirement: Time complexity = O(nlogn)
 */
public class Solution {
	public int[] countArray(int[] array) {
		int n = array.length;
		int[] ans = new int[n];
		Map<Integer, Integer> quickSortIndex = new HashMap<>();
		int pivot = n - 1;
		for (int i = n - 1; i >= 0; i--) {
			if (arr)
		}
	}
}
