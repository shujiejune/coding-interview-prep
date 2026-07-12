class Solution {
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList(head.next);
		ListNode tail = newHead;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = head;
		head.next = null;
		return newHead;
	}
}
