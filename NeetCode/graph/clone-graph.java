class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<>();
  }

  public Node(int _val, ArrayList<Node>_neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) return null;
    Map<Node, Node> oldToNew = new HashMap<>();
    Queue<Node> q = new LinkedList<>();
    oldToNew.put(node, new Node(node.val));
    q.add(node);
    while (!q.isEmpty()) {
      Node curr = q.poll();
      for (Node nei : curr.neighbors) {
        if (!oldToNew.containsKey(nei)) {
          oldToNew.put(nei, new Node(nei.val));
          q.add(nei);
        }
        oldToNew.get(curr).neighbors.add(oldToNew.get(nei));
      }
    }
    return oldToNew.get(node);
  }
}
