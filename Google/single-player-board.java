/* There is a single-player board game with N positions described by a string.
 * Eachposition canbe empty (denoted by '.'), contain a player's token (denoted by 'T')
 * or contain a coin ('C'). The player may possess multiple tokens.
 * A coin is collected by the player when a token is put on the coin's position 
 * (each coin can be collected only once).
 * In one turn, the player can move a token by exactly three positions to the right 
 * (the token does not stop on the positions in between). Every token can be moved 
 * multiple times. The token cannot be moved if there is already another token 
 * in the position it would move onto.
 * What is the maximum number of coins the player can collect?
 * 
 * Example:
 * For board = "TT.T.CCCCC" the player can move the third and second tokens twice,
 * collecting three coins in total:
 * "TT.T.CCCCC" -> "TT...CTCCC" -> "TT...C.CCT" -> "T...TC.CCT" -> "T....C.TCT"
 * It's still possible to move the first token, but it cannot collect any coins.
 */
public int play(String board) {
	if (board == null || board.length() <= 3) {
		return 0;
	}
	int n = board.length();
	char[] arr = board.toCharArray();
	int count = 0;
	for (int i = n - 4; i >= 0; i--) {
		if (arr[i] != 'T') continue;
		else {
			int curr = i;
			while (curr + 3 < n && arr[curr + 3] != 'T') {
				arr[curr] = '.';
				if (arr[curr + 3] == 'C') {
					count++;
				}
				arr[curr + 3] = 'T';
				curr += 3;
			}
		}
	}
	return count;
}

/* Optimized TS = O(N) */
public int play(String board) {
	if (board == null || board.length() <= 3) {
		return 0;
	}
	int n = board.length();
	int count = 0;
	for (int offset = 0; offset < 3; offset++) {
		int start = offset;
		while (start < n && board.charAt(start) != 'T') {
			start += 3;
		}
		for (int i = start; i < n; i+=3) {
			if (board.charAt(i) == 'C') {
				count++;
			}
		}
	}
	return count;
}
