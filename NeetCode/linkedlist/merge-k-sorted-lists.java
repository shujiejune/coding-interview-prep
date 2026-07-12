class Solution {
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
		for (ListNode list : lists) {
			pq.offer(list);
		}
		while (!pq.isEmpty()) {
			ListNode smallest = pq.poll();
			if (smallest.next != null) {
				pq.offer(smallest.next);
			}
			curr.next = smallest;
			curr = curr.next;
		}
		return dummy.next;
	}
}
