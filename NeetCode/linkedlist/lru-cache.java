class LRUCache {
	class Node {
		int key;
		int val;
		Node prev;
		Node next;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

	int capacity;
	Node head;
	Node tail;
	Map<Integer, Node> map;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.head = new Node(0, 0);
		this.tail = new Node(0, 0);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.map = new HashMap<>();
	}

	private void remove(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
	}

	private void insert(Node node) {
		Node prev = this.tail.prev;
		prev.next = node;
		node.prev = prev;
		node.next = this.tail;
		this.tail.prev = node;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			remove(node);
			insert(node);
			return node.val;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			remove(map.get(key));
		}
		Node add = new Node(key, value);
		map.put(key, add);
		insert(add);
		if (map.size() > capacity) {
			Node lru = this.head.next;
			remove(lru);
			map.remove(lru.key);
		}
	}
}
