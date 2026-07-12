boolean[] solution(long[] a) {
	int n = a.length;
	boolean[] ans = new boolean[n];
	long[] fibonacci = new long[100];
	fibonacci[0] = 1;
	fibonacci[1] = 1;
	for (int i = 2; i < 100; i++) {
		fibonacci[i] = fibonacci[i-2] + fibonacci[i-1];
	}
	for (int i = 0; i < n; i++) {
		ans[i] = twoSum(fibonacci, a[i]);
	}
	return ans;
}

boolean twoSum(long[] fibonacci, long target) {
	int right = findMaxIndex(fibonacci, target);
	int left = 0;
	while (left <= right) {
		long sum = fibonacci[left] + fibonacci[right];
		if (sum < target) {
			left++;
		} else if (sum > target) {
			right--;
		} else {
			return true;
		}
	}
	return false;
}

int findMaxIndex(long[] fibonacci, long target) {
	int left = 0, right = 99;
	while (left < right - 1) {
		int mid = (left + right) / 2;
		if (fibonacci[mid] < target) {
			left = mid;
		} else {
			right = mid - 1;
		}
	}
	return left;
}
