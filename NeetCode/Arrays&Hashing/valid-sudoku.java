/* You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

    Each row must contain the digits 1-9 without duplicates.
    Each column must contain the digits 1-9 without duplicates.
    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.

Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.
*/
class Solution {
	public boolean isValidSudoku(char[][] board) {
		Map<Integer, Set<Character>> rows = new HashMap<>();
		Map<Integer, Set<Character>> cols = new HashMap<>();
		Map<String, Set<Character>> squares = new HashMap<>();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board[r][c] == '.') continue;
				String squareKey = (r / 3) + "." + (c / 3);
				if (rows.computeIfAbsent(r, k->new HashSet<>()).contains(board[r][c])
				||  cols.computeIfAbsent(c, k->new HashSet<>()).contains(board[r][c])
				||  squares.computeIfAbsent(squareKey, k->new HashSet<>()).contains(board[r][c])) {
					return false;
				}
				rows.get(r).add(board[r][c]);
				cols.get(c).add(board[r][c]);
				squares.get(squareKey).add(board[r][c]);
			}
		}
		return true;
	}
}
