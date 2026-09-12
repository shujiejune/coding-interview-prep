class WordDictionary {
  class Node {
    char ch;
    boolean isEnd;
    List<Node> children;

    public Node(char c) {
      this.ch = c;
      this.isEnd = false;
      this.children = new ArrayList<>();
    }
  }

  Node root;

  public WordDictionary() {
    root = new Node('*');
  }

  public void addWord(String word) {
    Node curr = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      boolean exist = false;
      List<Node> children = curr.children;
      for (Node child : children) {
        if (c == child.ch) {
          exist = true;
          curr = child;
          break;
        }
      }
      if (!exist) {
        Node newChild = new Node(c);
        children.add(newChild);
        curr = newChild;
      }
    }
    curr.isEnd = true;
  }

  public boolean search(String word) {
    boolean[] ans = new boolean[1];
    dfs(word, 0, root, ans);
    return ans[0];
  }

  private void dfs(String word, int i, Node curr, boolean[] ans) {
    if (i == word.length()) {
      ans[0] = curr.isEnd;
      return;
    }
    char c = word.charAt(i);
    List<Node> children = curr.children;
    if (c == '.') {
      for (Node child : children) {
        dfs(word, i + 1, child, ans);
      }
    } else {
      for (Node child : children) {
        if (child.ch == c) {
          dfs(word, i + 1, child, ans);
          break;
        }
      }
    }
  }
}
