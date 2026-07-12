class Solution {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length, len2 = nums2.length;
		int left = (len1 + len2 + 1) / 2;
		int right = (len1 + len2 + 2) / 2;
		return (getKth(nums1, nums2, len1, len2, left, 0, 0) +
			getKth(nums1, nums2, len1, len2, right, 0, 0)) / 2.0;
	}

	private int getKth(int[] a, int[] b, int m, int n, int k, int l1, int l2) {
		if (m > n) {
			return getKth(b, a, n, m, k, l2, l1);
		}
		if (m == 0) {
			return b[l2 + k - 1];
		}
		if (k == 1) {
			return Math.min(a[l1], b[l2]);
		}
		int i = Math.min(m, k / 2);
		int j = Math.min(n, k / 2);
		if (a[l1 + i - 1] > b[l2 + j - 1]) {
			return getKth(a, b, m, n - j, k - j, l1, l2 + j);
		} else {
			return getKth(a, b, m - i, n, k - i, l1 + i, l2);
		}
	}
}

// move cut solution
class Solution {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] A = nums1;
		int[] B = nums2;
		int total = A.length + B.length;
		int half = (total + 1) / 2;

		if (B.length < A.length) {
			int[] temp = A;
			A = B;
			B = temp;
		}

		int l = 0, r = A.length;
		while (l <= r) {
			int i = (l + r) / 2;
			int j = half - i;
			int Aleft = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
			int Aright = i < A.length ? A[i] : Integer.MAX_VALUE;
			int Bleft = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
			int Bright = j < B.length ? B[j] : Integer.MAX_VALUE;
			if (Aleft <= Bright && Bleft <= Aright) {
				if (total % 2 != 0) {
					return Math.max(Aleft, Bleft);
				} else {
					return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
				}
			} else if (Aleft > Bright) {
				r = i - 1;
			} else {
				l = i + 1;
			}
		}
		return -1;
	}
}
