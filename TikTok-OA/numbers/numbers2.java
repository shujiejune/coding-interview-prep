int[] solution(int[] numbers) {
	Deque<Integer> greater1 = new ArrayDeque<>();
	Deque<Integer> greater2 = new ArrayDeque<>();
	Deque<Integer> aux1 = new ArrayDeque<>();
	Deque<Integer> aux2 = new ArrayDeque<>();
	int len1 = 1, len2 = 1, n = numbers.length;
	greater1.offerFirst(numbers[0]);
	greater2.offerFirst(numbers[1]);
	for (int i = 2; i < n; i++) {
		int curr = numbers[i];
		if (greater1.peekFirst() <= curr) {
			while (!greater1.isEmpty() && greater1.peekFirst() <= curr) {
				aux1.offerFirst(greater1.pollFirst());
			}
		}
		if (greater2.peekFirst() <= curr) {
			while (!greater2.isEmpty() && greater2.peekFirst() <= curr) {
				aux2.offerFirst(greater2.pollFirst());
			}
		}
		boolean goto1 = true;
		if (greater1.size() < greater2.size()) {
			goto1 = false;
		}
		if (greater1.size() == greater2.size() && len1 > len2) {
			goto1 = false;
		}
		if (goto1) {
			greater1.offerFirst(curr);
			len1++;
		} else {
			greater2.offerFirst(curr);
			len2++;
		}
		while (!aux1.isEmpty()) {
			greater1.offerFirst(aux1.pollFirst());
		}
		while (!aux2.isEmpty()) {
			greater2.offerFirst(aux2.pollFirst());
		}
	}
	while (!greater1.isEmpty()) {
		aux1.offerFirst(greater1.pollFirst());
	}
	while (!greater2.isEmpty()) {
		aux2.offerFirst(greater2.pollFirst());
	}
	int[] ans = new int[n];
	int i = 0;
	while (!aux1.isEmpty()) {
		ans[i++] = aux1.pollFirst();
	}
	while (!aux2.isEmpty()) {
		ans[i++] = aux2.pollFirst();
	}
	return ans;
}
