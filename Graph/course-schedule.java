/* There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to 
 * first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, 
 * return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. 
 * If it is impossible to finish all courses, return an empty array.
 */
public class Solution {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] indegree = new int[numCourses];
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int[] pair : prerequisites) {
			int pre = pair[1];
			int after = pair[0];
			indegree[after]++;
			if (!graph.containsKey(pre)) {
				graph.put(pre, new ArrayList<>());
			}
			graph.get(pre).add(after);
		}
		Queue<Integer> q = new ArrayDeque<>();
		int count = 0;
		int[] order = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.offerFirst(i);
			}
		}
		if (count == 0) {
			return new int[]{};
		}
		while (!q.isEmpty()) {
			int curr = q.pollFirst();
			order[count++] = curr;
			List<Integer> children = graph.get(curr);
			for (int child : children) {
				indegree[child]--;
				if (indegree[child] == 0) {
					q.offerFirst(child);
				}
			}
		}
		if (count == numCourses) {
			return order;
		} else {
			return new int[]{};
		}
	}
}

/* TC: O(V+E)
 * SC: O(V+E)
 */
