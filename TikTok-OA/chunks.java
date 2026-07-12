/*
 * A user is uploading a big file to server. The upload is done
 * by sending file chunks of consecutive byte ranges, and the 
 * byte ranges for all chunks are represented as a two-dimensional
 * array chunks. For each chunk chunks[i], the byte range is stored
 * in an array of two 64-bit integers: chunks[i][0] is the leftmost
 * byte in the i-th chunk, and chunks[i][1] is the rightmost byte
 * in the i-th chunk. Both indices are inclusive, 1-based.
 * Your task is to determine the total number of bytes (of the overall
 * file) received after each file chunk is received.
 * Note that chunks may intersect or even fully replicate the previous
 * ones, i.e. sending the same data twice.
 * Example: for chunks = [[1, 1], [2, 2], [3, 3]], the output should be
 * getBytes(chunks) = [1, 2, 3].
 */
int[] getBytes(int[][] chunks) {
	int n = chunks.length;
	if (chunks == null || n == 0) {
		return new int[0];
	}
	Arrays.sort(chunks, (c1, c2) -> Integer.compare(c1[0], c2[0]));
	List<int[]> merged = new ArrayList<>();
	int[] curr = chunks[0];
	merged.add(curr);
	for (int i = 1; i < n; i++) {
		int[] next = chunks[i];
		if (next[0] <= curr[1]) {
			curr[1] = Math.max(next[1], curr[1]);
		} else {
			merged.add(next);
			curr = next;
		}
	}
	List<Integer> bytes = new ArrayList<>();
	for (int[] interval : merged) {
		bytes.addAll(interval);
	}
	int len = bytes.size();
	int[] ans = new int[len];
	for (int i = 0; i < len; i++) {
		ans[i] = (int) bytes.get(i);
	}
	return ans;
}
