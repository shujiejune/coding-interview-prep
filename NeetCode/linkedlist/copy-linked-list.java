class Solution {
	Map<Node, Node> map = new HashMap<>();

	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		if (map.containsKey(head)) {
			return map.get(head);
		}
		Node copy = new Node(head.val);
		map.put(head, copy);
		copy.next = copyRandomList(head.next);
		copy.random = map.get(head.random);
		return copy;
	}
}

// space optimize I
class Solution {
	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		Node l1 = head;
		while (l1 != null) {
			Node l2 = new Node(l1.val);
			l2.next = l1.next;
			l1.next = l2;
			l1 = l2.next;
		}
		Node newHead = head.next;
		l1 = head;
		while (l1 != null) {
			if (l1.random != null) {
				l1.next.random = l1.random.next;
			}
			l1 = l1.next.next;
		}
		l1 = head;
		while (l1 != null) {
			Node l2 = l1.next;
			l1.next = l2.next;
			if (l2.next != null) {
				l2.next = l2.next.next;
			}
			l1 = l1.next;
		}
		return newHead;
	}
}
