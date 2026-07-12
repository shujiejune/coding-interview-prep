/* 1. Game Background

    Players: 2 players, taking turns.

    Pieces: 12 tiles total.

        4 colors: Yellow (Y), White (W), Green (G), Blue (B).

        3 tiles per color.

    Initial State: 12 independent stacks, each with a height of 1.

    Stack Color: The color of a stack is defined by the color of its top tile.

    2. Movement Rules (Move) A legal move consists of taking an entire stack and 
    placing it on top of another stack (merging them). This is allowed only if 
    one of the following conditions is met:

    Height Match: The two stacks have the same height.

    Color Match: The top tiles of both stacks have the same color.

    After Merging:

    New Height: Sum of the heights of the two merged stacks.

    New Color: The color of the stack that was moved to the top.

    3. Winning Conditions

    This is a "Last Player to Move Wins" game (Normal Play Convention).

    If it is your turn and no legal moves are possible, you lose.
 */
class Stack {
	int height;
	int color; // 1: Yellow, 2: White, 3: Green, 4: Blue
	
	Stack(int h, int c) {
		this.height = h;
		this.color = c;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Stack)) return false;
		Stack other = (Stack) o;
		return height == other.height && color == other.color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(height, color);
	}
}

public class BabylonSolver {
	private Map<String, boolean> memo = new HashMap<>();

	public boolean canWin(List<Stack> currStacks) {
		currStacks.sort((a, b) -> {
			if (a.height != b.height) {
				return a.height - b.height;
			}
			return a.color - b.color;
		});
		String key = getStateKey(currStacks);
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		for (int i = 0; i < currStacks.size(); i++) {
			for (int j = 0; j < currStacks.size(); j++) {
				if (i == j) continue;
				Stack s1 = currStacks.get(i);
				Stack s2 = currStacks.get(j);
				if (s1.equals(s2)) {
					List<Stack> nextStacks = new ArrayList<>(currStacks);
					nextStacks.remove(s1);
					nextStacks.remove(s2);
					nextStacks.add(new Stack(s1.height + s2.height, s1.color));
					if (!canWin(nextStacks)) {
						memo.put(currStacks, true);
						return true;
					}
				}
			}
		}
		memo.put(key, false);
		return false;
	}
}
