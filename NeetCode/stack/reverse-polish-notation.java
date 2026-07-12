class Solution {
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (String token : tokens) {
			if (token.equals("+")) {
				int num2 = stack.pollLast();
				int num1 = stack.pollLast();
				stack.offerLast(num1 + num2);
			} else if (token.equals("-")) {
				int num2 = stack.pollLast();
				int num1 = stack.pollLast();
				stack.offerLast(num1 - num2);
			} else if (token.equals("*")) {
				int num2 = stack.pollLast();
				int num1 = stack.pollLast();
				stack.offerLast(num1 * num2);
			} else if (token.equals("/")) {
				int num2 = stack.pollLast();
				int num1 = stack.pollLast();
				stack.offerLast(num1 / num2);
			} else {
				stack.offerLast(Integer.valueOf(token));
			}
		}
		return stack.peekLast();
	}
}
