class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord) || beginWord.equals(endWord)) {
      return 0;
    }
    int n = wordList.size();
    int m = beginWord.length();
    List<List<Integer>> adj = new ArrayList<>();
    Map<String, Integer> mp = new HashMap<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
      mp.put(wordList.get(i), i);
    }
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int count = 0;
        for (int k = 0; k < m; k++) {
          if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)) {
            count++;
          }
        }
        if (count == 1) {
          adj.get(i).add(j);
          adj.get(j).add(i);
        }
      }
    }
    Queue<Integer> q = new LinkedList<>();
    int ans = 1;
    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < m; i++) {
      for (char c = 'a'; c <= 'z'; c++) {
        if (c == beginWord.charAt(i)) continue;
        String word = beginWord.substring(0, i) + c + beginWord.substring(i + 1, m);
        if (mp.containsKey(word) && !visited.contains(mp.get(word))) {
          q.offer(mp.get(word));
          visited.add(mp.get(word));
        }
      }
    }
    while (!q.isEmpty()) {
      ans++;
      int sz = q.size();
      for (int i = 0; i < sz; i++) {
        int curr = q.poll();
        if (wordList.get(curr).equals(endWord)) {
          return ans;
        }
        for (int nei : adj.get(curr)) {
          if (!visited.contains(nei)) {
            q.offer(nei);
            visited.add(nei);
          }
        }
      }
    }
    return 0;
  }
}
