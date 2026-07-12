class Solution {
	public void reorderList(ListNode head) {
		if (head.next == null || head.next.next == null) {
			return;
		}
		ListNode tail = head;
		ListNode secondLast = null;
		while (tail.next != null) {
			secondLast = tail;
			tail = tail.next;
		}
		ListNode second = head.next;
		head.next = tail;
		secondLast.next = null;
		reorderList(second);
		tail.next = second;
	}
}
