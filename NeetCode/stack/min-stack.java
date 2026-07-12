class MinStack {
	Deque<Integer> stack;
	Deque<Integer> desc;

	public MinStack() {
		stack = new ArrayDeque<>();
		desc = new ArrayDeque<>();
	}

	public void push(int val) {
		stack.offerLast(val);
		if (desc.isEmpty() || desc.peekLast() >= val) {
			desc.offerLast(val);
		}
	}

	public void pop() {
		int top = stack.pollLast();
		if (top == desc.peekLast()) {
			desc.pollLast();
		}
	}

	public int top() {
		return stack.peekLast();
	}

	public int getMin() {
		return desc.peekLast();
	}
}
