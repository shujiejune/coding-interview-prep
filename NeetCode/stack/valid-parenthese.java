class Solution {
	public boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				stack.offerLast(c);
			} else if (c == ')') {
				if (stack.isEmpty() || stack.peekLast() != '(') {
					return false;
				} else {
					stack.pollLast();
				}
			} else if (c == ']') {
				if (stack.isEmpty() || stack.peekLast() != '[') {
					return false;
				} else {
					stack.pollLast();
				}
			} else if (c == '}') {
				if (stack.isEmpty() || stack.peekLast() != '{') {
					return false;
				} else {
					stack.pollLast();
				}
			} else {
				return false;
			}
		}
		if (!stack.isEmpty()) {
			return false;
		}
		return true;
	}
}
