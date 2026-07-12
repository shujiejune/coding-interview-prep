class TwoSum {
	private ArrayList<Integer> nums;
	private boolean isSorted;

	public TwoSum() {
		this.nums = new ArrayList<>();
		this.isSorted = false;
	}

	public void add(int number) {
		nums.add(number);
		isSorted = false;
	}

	public boolean find(int value) {
		if (!isSorted) {
			Collections.sort(nums);
			isSorted = true;
		}
		int l = 0, r = nums.size() - 1;
		while (l < r) {
			int sum = nums.get(l) + nums.get(r);
			if (sum < value) {
				l++;
			} else if (sum > value) {
				r--;
			} else {
				return true;
			}
		}
		return false;
	}
}
