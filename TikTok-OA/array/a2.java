int solution(int[] a) {
	int[] numsOf1 = new int[32];
	for (int ele : a) {
		numsOf1[numsOf1inBinary(ele)]++;
	}
	int ans = 0;
	for (int i = 0; i < 32; i++) {
		if (numsOf1[i] > 0) {
			ans += numsOf1[i] * (numsOf1[i] - 1) / 2;
		}
	}
	return ans;
}

int numsOf1inBinary(int n) {
	return Integer.bitCount(n)
}
