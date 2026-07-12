public class Solution {
	public int[] quickSort(int[] array) {
		// edge cases
		if (array == null || array.length <= 1) {
			return array;
		}
		helper(array, 0, array.length - 1);
		return array;
	}

	private void helper(int[] array, int left, int right) {
		// base case
		if (left > right) {
			return;
		}
		int pivotIndex = partition(array, left, right);
		helper(array, left, pivotIndex - 1);
		helper(array, pivotIndex + 1, right);
	}

	private int partition(int[] array, int left, int right) {
		int pivotIndex = getRandom(left, right);
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, right);
		int i = left, j = right - 1;
		while (i <= j) {
			if (array[i] <= pivot) {
				i++;
			} else {
				swap(array, i, j);
				j--;
			}
		}
		swap(array, i, right);
		return i;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
