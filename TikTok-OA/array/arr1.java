int[] solution(int[] arr) {
	int n = arr.length;
	int[] ans = new int[n - 2];
	for (int i = 0; i < n - 2; i++) {
		if (arr[i] < arr[i + 1] && arr[i + 1] < arr[i + 2]) {
			ans[i] = 1;
		}
		if (arr[i] > arr[i + 1] && arr[i + 1] > arr[i + 2]) {
			ans[i] = 1;
		}
	}
	return ans;
}
