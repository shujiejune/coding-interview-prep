class Solution {
  int m, n;
  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public List<String> findWords(char[][] board, String[] words) {
    List<String> ans = new ArrayList<>();
    m = board.length;
    n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      char head = word.charAt(0);
      for (int x = 0; x < m; x++) {
        for (int y = 0; y < n; y++) {
          if (head == board[x][y]) {
            visited[x][y] = true;
            dfs(board, x, y, 1, word, visited, ans);
            visited[x][y] = false;
          }
        }
      }
    }
    return ans;
  }

  private void dfs(char[][] board, int i, int j, int index, String word, boolean[][] visited, List<String> ans) {
    if (index == word.length()) {
      ans.add(word);
      return;
    }
    for (int[] d : directions) {
      int x = i + d[0];
      int y = j + d[1];
      if (isValid(m, n, x, y) && !visited[x][y]) {
        if (board[x][y] == word.charAt(index)) {
          visited[x][y] = true;
          dfs(board, x, y, index + 1, word, visited, ans);
          visited[x][y] = false;
        }
      }
    }
  }

  private boolean isValid(int row, int col, int x, int y) {
    return x >= 0 && x < row && y >= 0 && y < col;
  }
}

// Trie and Backtracking
class TrieNode {
  Map<Character, TrieNode> children;
  boolean isEnd;

  public TrieNode() {
    children = new HashMap<>();
    isEnd = false;
  }

  public void addWord(String word) {
    TrieNode curr = this;
    for (char c : word.toCharArray()) {
      curr.children.putIfAbsent(c, new TrieNode());
      curr = curr.children.get(c);
    }
    curr.isEnd = true;
  }
}

public class Solution {
  private Set<String> ans;
  private boolean[][] visited;

  public List<String> findWords(char[][] board, String[] words) {
    TrieNode root = new TrieNode();
    for (String word : words) {
      root.addWord(word);
    }
    int m = board.length, n = board[0].length;
    ans = new HashSet<>();
    visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, i, j, root, "");
      }
    }
    return new ArrayList<>(ans);
  }

  private void dfs(char[][] board, int i, int j, TrieNode node, String word) {
    int m = board.length, n = board[0].length;
    if (!isValid(m, n, i, j) || visited[i][j]
        || !node.children.containsKey(board[i][j]) {
      return;
    }
    visited[i][j] = true;
    node = node.children.get(board[i][j]);
    word += board[i][j];
    if (node.isEnd) {
      ans.add(word);
    }

    dfs(board, i + 1, j, node, word);
    dfs(board, i - 1, j, node, word);
    dfs(board, i, j + 1, node, word);
    dfs(board, i, j - 1, node, word);

    visited[i][j] = false;
  }
}
