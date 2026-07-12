class Solution {
	public int minEatingSpeed(int[] piles, int h) {
		Arrays.sort(piles);
		int n = piles.length;
		int l = 1, r = piles[n - 1];
		while (l < r) {
			int mid = l + (r - l) / 2;
			int k = getHours(piles, mid);
			if (k > h) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;
	}

	private int getHours(int[] piles, int speed) {
		int sum = 0;
		for (int i = 0; i < piles.length; i++) {
			sum += Math.ceil((double) piles[i] / speed);
		}
		return sum;
	}
}

// Use Stream API
class Solution {
	public int minEatingSpeed(int[] piles, int h) {
		int l = 1;
		int r = Arrays.stream(piles).max().getAsInt();
		int ans = r;
		while (l <= r) {
			int k = l + (r - l) / 2;
			int totalTime = getHours(piles, k);
			if (totalTime <= h) {
				ans = k;
				r = k - 1;
			} else {
				l = k + 1;
			}
		}
		return ans;
	}

	private int getHours(int[] piles, int speed) {
		int sum = 0;
		for (int i = 0; i < piles.length; i++) {
			sum += Math.ceil((double) piles[i] / speed);
		}
		return sum;
	}
}
