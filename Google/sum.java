/* There is an array consisting of N integers. 
 * Choose at most one element to multiply by -1
 * in order to obtain an array whose sum of 
 * elements is as close to 0 as possible.
 * That is, find the sum with the minimum 
 * absolute value.
 */
public int sum(int[] array) {
	int sum = 0;
	int n = array.length;
	for (int i = 0; i < n; i++) {
		sum += array[i];
	}
	int diff = Math.abs(sum);
	for (int i = 0; i < n; i++) {
		int newSum = sum - 2 * array[i];
		diff = Math.min(diff, Math.abs(newSum));
	}
	return diff;
}
