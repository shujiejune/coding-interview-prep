/* An array consists of N two-digit numbers. A group of numbers can be chosen
 * from the array only if all of them share at least one digit. For example, 
 * numbers 52, 25 and 55 can be chosen together (they share digit 5), but 
 * 11, 52 and 34 cannot. What is the maximum number of array elements that 
 * can be chosen together?
 */
public int shareDigit(int[] array) {
	if (array == null || array.length == 0) {
		return 0;
	}
	int[] count = new int[10];
	for (int num : array) {
		int left = num / 10;
		int right = num % 10;
		count[left]++;
		if (right != left) {
			count[right]++;
		}
	}
	int ans = 0;
	for (int i = 0; i < 10; i++) {
		ans = Math.max(ans, count[i]);
	}
	return ans;
}
