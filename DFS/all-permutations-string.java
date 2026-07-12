/* Given a string without duplicate letters,
 * print out all permutations of the string.
 */
public List<String> permutations(String input) {
  Set<Character> chars = new HashSet<>();
  for (char c : input.toCharArray()) {
    chars.add(c);
  }
  List<String> ans = new ArrayList<>();
  dfs(chars, new StringBuilder(), ans);
  return ans;
}

private void dfs(Set<Character> chars, StringBuilder s, List<String> ans) {
  if (chars.size() == 0) {
    ans.add(s.toString());
    return;
  }
  Iterator<Character> iter = chars.iterator();
  while (iter.hasNext()) {
    char c = iter.next();
    s.append(c);
    chars.remove(c);
    dfs(chars, s, ans);
    chars.add(c);
    s.deleteCharAt(s.length() - 1);
  }
}

/* Why the solution above is incorrect:
 * Modifying the set while iterating through it.
 */

public List<String> permutations(String input) {
  List<String> ans = new ArrayList<>();
  if (input == null) {
    return ans;
  }
  char[] chars = input.toCharArray();
  Arrays.sort(chars);
  boolean[] visited = new boolean[input.length()];
  dfs(chars, visited, new StringBuilder(), ans);
  return ans;
}

private void dfs(char[] chars, boolean[] visited, StringBuilder s, List<Stirng> ans) {
  if (s.length() == chars.length) {
    ans.add(s.toString());
    return;
  }
  for (int i = 0; i < chars.length; i++) {
    if (visited[i]) { continue; }
    if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
      continue;
    }
    visited[i] = true;
    s.append(chars[i]);
    dfs(chars, visited, s, ans);
    s.deleteCharAt(s.length() - 1);
    visited[i] = false;
  }
}

/* Better solution */
public List<String> permutations(String input) {
  List<String> ans = new ArrayList<>();
  if (input == null) {
    return ans;
  }
  char[] chars = input.toCharArray();
  dfs(chars, 0, ans);
  return ans;
}

private void dfs(char[] chars, int index, List<String> ans) {
  if (index == chars.length) {
    ans.add(new String(chars));
    return;
  }
  for (int i = index; i < chars.length; i++) {
    swap(chars, index, i);
    dfs(chars, index + 1, ans);
    swap(chars, index, i);
  }
}

private void swap(char[] arr, int i, int j) {
  char temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

/* Given a string with duplicate letters,
 * print out all permutations of the string.
 */
public List<String> permutations(String input) {
  List<String> ans = new ArrayList<>();
  if (input == null || input.length() == 0) {
    return ans;
  }
  Map<Character, Integer> count = new HashMap<>();
  for (char c : input.toCharArray()) {
    count.put(c, count.getOrDefault(c, 0) + 1);
  }
  int n = input.length();
  dfs(count, 0, n, new StringBuilder(), ans);
  return ans;
}

private void dfs(Map<Character, Integer> count, int index, int n, StringBuilder s, List<String> ans) {
  if (index == n) {
    ans.add(s.toString());
    return;
  }
  Iterator<Character> iter = count.keySet().iterator();
  while (iter.hasNext()) {
    char c = iter.next();
    if (count.get(c) == 0) {
      continue;
    } else {
      count.put(c, count.get(c) - 1);
    }
    s.append(c);
    dfs(count, index + 1, n, s, ans);
    s.deleteCharAt(s.length() - 1);
    count.put(c, count.get(c) + 1);
  }
}

/* TC: O(K^m), K is the total number of permutations, m is the number of unique chars in input
 * SC: O(n)
 */
