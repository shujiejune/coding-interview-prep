/* Say I have a map of a city, represented by m by n grid. 
 * I have libraries and train stations. I am going to return 
 * a book to the library, and want to walk as little possible. 
 * Given a map, give me my best route to any library. Grid 
 * and start position are provided as input，and we know the 
 * walking distances from train stations to libraries. 
 */
public class Solution {
	private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	class Node {
		int r;
		int c;
		int dist;

		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	public int findBestRoute(int[][] grid, Map<String, Integer> stationToLib, int[] start) {
		int m = grid.length, n = grid[0].length;
		// minDistance[i][j] is the min walking distance from start to (i, j)
		int[][] minDistance = new int[m][n];
		for (int[] row : minDistance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		minDistance[start[0]][start[1]] = 0;
		pq.offer(new Node(start[0], start[1], 0));
		int globalMin = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (curr.dist > minDistance[curr.r][curr.c]) { continue; }
			// if curr cell is a library
			if (grid[curr.r][curr.c] == 1) {
				globalMin = Math.min(globalMin, curr.dist);
			} else if (grid[curr.r][curr.c] == 2) { // if curr cell is a station
				String station = curr.r + "," + curr.c;
				if (stationToLib.containsKey(key)) {
					globalMin = Math.min(globalMin, curr.dist + stationToLib.get(key));
				}
			}
			for (int[] d : directions) {
				int x = curr.r + d[0];
				int y = curr.c + d[1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int nextDist = curr.dist + 1;
					pq.offer(new Node(x, y, nextDist));
				}
			}

		}
		return globalMin == Integer.MAX_VALUE ? -1 : globalMin;
	}
}
