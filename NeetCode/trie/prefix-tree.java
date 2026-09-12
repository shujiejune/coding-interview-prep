class PrefixTree {
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

  public PrefixTree() {
    root = new Node('*');
  }

  public void insert(String word) {
    Node curr = root;
    for (int i = 0; i < word.length(); i++) {
      List<Node> children = curr.children;
      char c = word.charAt(i);
      boolean exist = false;
      for (Node child : children) {
        if (c == child.ch) {
          curr = child;
          exist = true;
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
    Node curr = root;
    for (int i = 0; i < word.length(); i++) {
      List<Node> children = curr.children;
      char c = word.charAt(i);
      boolean exist = false;
      for (Node child : children) {
        if (c == child.ch) {
          exist = true;
          curr = child;
          break;
        }
      }
      if (!exist) return false;
    }
    return curr.isEnd;
  }

  public boolean startsWith(String prefix) {
    Node curr = root;
    for (int i = 0; i < prefix.length(); i++) {
      List<Node> children = curr.children;
      char c = prefix.charAt(i);
      boolean exist = false;
      for (Node child : children) {
        if (c == child.ch) {
          exist = true;
          curr = child;
          break;
        }
      }
      if (!exist) return false;
    }
    return true;
  }
}
