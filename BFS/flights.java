/**
 * Suppose there are m flights connecting n cities. Flight is represented by an int array int[] where the first element is departure city, the second element is destination city and the third element is the price.

 * Given a departure city src, a destination city dst, and most number of stops k, return the lowest price of flights can take you from src to dst with at most k stops. If there is no such a route, then return -1.

 * You can assume that there is no duplicated flights.
*/
public class Solution {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    // Write your solution here
    // key: dst, val: {src, price}
    Map<Integer, List<int[]>> graph = new HashMap<>();
    int[] indegree = new int[n];
    for (int[] f : flights) {
      int currSrc = f[0], currDst = f[1], price = f[2];
      indegree[currDst]++;
      if (graph.containsKey(currDst)) {
        graph.get(currDst).add(new int[]{currSrc, price});
      } else {
        List<int[]> priors = new ArrayList<>();
        priors.add(new int[]{currSrc, price});
        graph.put(currDst, priors);
      }
    }
    int stops = 0;
    Queue<Integer> q = new LinkedList<>();
    int[] shortestFromDst = new int[n];
    boolean[] visited = new boolean[n];
    q.offer(dst);
    Arrays.fill(shortestFromDst, -1);
    shortestFromDst[dst] = 0;
    visited[dst] = true;
    while (!q.isEmpty() && stops <= k) {
      stops++;
      int sz = q.size();
      for (int i = 0; i < sz; i++) {
        int city = q.poll();
        if (!graph.containsKey(city)) { continue; }
        List<int[]> priors = graph.get(city);
        for (int[] p : priors) {
          q.offer(p[0]);
          int pathSum = shortestFromDst[city] + p[1];
          if (visited[p[0]]) {
            shortestFromDst[p[0]] = Math.min(pathSum, shortestFromDst[p[0]]);
          } else {
            shortestFromDst[p[0]] = pathSum;
          }
          visited[p[0]] = true;
        }
      }
    }
    return shortestFromDst[src];
  }
}
