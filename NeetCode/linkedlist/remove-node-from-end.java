class Solution {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode tail = reverse(head);
		ListNode curr = tail;
		if (n == 1) {
			if (tail.next == null) {  // cannot use head.next == null here
				return null;
			} else {
				curr = curr.next;
				tail.next = null;
				tail = curr;
			}
		} else {
			for (int i = 1; i < n - 1; i++) {
				curr = curr.next;
			}
			ListNode removed = curr.next;
			curr.next = curr.next.next;
			removed.next = null;
		}
		return reverse(tail);
	}

	private ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverse(head.next);
		ListNode curr = newHead;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = head;
		head.next = null;
		return newHead;
	}
}
