public ListNode reverse(ListNode head) {
	if (head == null || head.next == null) {
		return head;
	}
	ListNode second = head.next;
	ListNode newHead = reverse(second);
	second.next = head;
	head.next = null;
	return newHead;
}

/* reverse pair by pair */
public ListNode reverse(ListNode head) {
	if (head == null || head.next == null) {
		return head;
	}
	ListNode second = head.next;
	ListNode third = second.next;
	third = reverse(third);
	head.next = third;
	second.next = head;
	return second;
}
