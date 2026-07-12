class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		int carry = 0;
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + carry;
			int digit = sum % 10;
			carry = sum / 10;
			curr.next = new ListNode(digit);
			curr = curr.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		if (l1 != null) {
			while (l1 != null) {
				int sum = l1.val + carry;
				curr.next = new ListNode(sum % 10);
				carry = sum / 10;
				curr = curr.next;
				l1 = l1.next;
			}
		}
		if (l2 != null) {
			while (l2 != null) {
				int sum = l2.val + carry;
				curr.next = new ListNode(sum % 10);
				carry = sum / 10;
				curr = curr.next;
				l2 = l2.next;
			}
		}
		if (carry != 0) {
			curr.next = new ListNode(carry);
		}
		return dummy.next;
	}
}
