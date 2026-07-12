/* Given an array, find the value of the first smaller element
 * on the right side of each element.
 */
public class Solution {
	public int[] firstSmallerRight(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		int n = array.length;
		int[] ans = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && array[stack.peekLast()] >= array[i]) {
				ans[stack.pollLast()] = array[i];
			}
			stack.offerLast(i);
		}
		// clarification: what about the rightmost element? what if all the right elements are greater?
		while (!stack.isEmpty()) {
			ans[stack.pollLast()] = -1;
		}
		return ans;
	}
}

/* next greater number
 * Given two integer arrays all and partial without duplicate numbers, 
 * array partial is subset of array all. For each number in partial, 
* find the first number on its right in all that greater than it. 
* If no such number existed, then assign the result to -1.
*/
public class Solution {
	public int[] nextGreaterElement(int[] partial, int[] all) {
		if (all == null || partial == null) {
			return null;
		}
		int m = all.length, n = partial.length;
		Deque<Integer> stack = new ArrayDeque<>();
		Map<Integer, Integer> nextGreater = new HashMap<>();
		Set<Integer> partialSet = new HashSet<>();
		for (int num : partial) {
			partialSet.add(num);
			nextGreater.put(num, -1);
		}
		for (int i = 0; i < m; i++) {
			while (!stack.isEmpty() && all[stack.peekLast()] <= all[i]) {
				int top = all[stack.pollLast()];
				if (partialSet.contains(top)) {
					nextGreater.put(top, all[i]);
				}
			}
			stack.offerLast(i);
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = nextGreater.get(partial[i]);
		}
		return ans;
	}
}
