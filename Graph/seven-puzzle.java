/* Given eight cards with 0-7 on them, the cards are aligned to be a 4x2 board (2 rows, 4 columns).
 * In each step only card 0 can be swap with one of its adjacent (top, right, bottom, left) card.
 * Find the minimum number of steps from the given state to the final state:
 * 0 1 2 3
 * 4 5 6 7
 * If there is no way to the final state, return -1.
 */
final static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

static class Board {
	int[][] state;

	public Board(int[] values) {
		for (int i = 0; i < values.length; i++) {
			int row = i / 4;
			int col = i % 4;
			state[row][col] = values[i];
		}
	}

	public boolean isValid(int i, int j) {
		return i >= 0 && i < 2 && j >= 0 && j < 4;
	}

	public int[] findZero() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] == 0) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	public void swap(int r1, int c1, int r2, int c2) {
		int temp = state[r1][c1];
		state[r1][c1] = state[r2][c2];
		state[r2][c2] = temp;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceOf Board)) {
			return false;
		}
		Board b = (Board) o;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] != b.state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j< 4; j++) {
				code = code * 10 + state[i][j];
			}
		}
		return code;
	}

	@Override
	public Board clone() {
		Board c = new Board();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				c.state[i][j] = state[i][j];
			}
		}
		return c;
	}
}

public int numOfSteps(int[] values) {
	Queue<Board> q = new ArrayDeque<>();
	Map<Board, Integer> boardSteps = new HashMap<>();
	Board final = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
	q.offerFirst(final);
	boardSteps.put(final, 0);
	while (!q.isEmpty()) {
		Board curr = q.poll();
		int[] coord0 = curr.findZero();
		for (int[] d : directions) {
			int x = coord0[0] + d[0];
			int y = coord0[1] + d[1];
			if (curr.isValid(x, y)) {
				curr.swap(coord0[0], coord0[1], x, y);
				if (!boardSteps.containsKey(curr)) {
					Board next = curr.clone();
					q.offer(next);
					boardSteps.put(next, boardSteps.get(curr) + 1);
				}
				curr.swap(coord0[0], coord0[1], x, y);
			}
		}
	}
	return boardSteps.getOrDefault(new Board(values), -1);
}
