class Solution {
	public int maxProfit(int[] prices) {
		int ans = 0;
		int in = prices[0];
		for (int p : prices) {
			ans = Math.max(ans, p - in);
			in = Math.min(in, p);
		}
		return ans;
	}
}

class Solution {
	public int maxProfit(int[] prices) {
		int ans = 0;
		int i = 0, j = 1;
		while (j < prices.length) {
			if (prices[i] < prices[j]) {
				int profit = prices[j] - prices[i];
				ans = Math.max(ans, profit);
			} else {
				i = j;
			}
			j++;
		}
		return ans;
	}
}
