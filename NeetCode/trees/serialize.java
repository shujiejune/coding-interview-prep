// DFS
public class Codec {
	// Encodes a tree to a single string
	public String serialize(TreeNode root) {
		List<String> ans = new ArrayList<>();
		dfsEncode(root, ans);
		return String.join(",", ans);
	}

	private void dfsEncode(TreeNode root, List<String> ans) {
		if (root == null) {
			ans.add("N");
			return;
		}
		ans.add(String.valueOf(root.val));
		dfsEncode(root.left, ans);
		dfsEncode(root.right, ans);
	}

	// Decodes the encoded data to tree
	public TreeNode deserialize(String data) {
		String[] vals = data.split(",");
		int[] i = {0};
		return dfsDecode(vals, i);
	}

	private TreeNode dfsDecode(String[] vals, int[] i) {
		if (vals[i[0]].equals("N")) {
			i[0]++;
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
		i[0]++;
		node.left = dfsDecode(vals, i);
		node.right = dfsDecode(vals, i);
		return node;
	}
}

// BFS
public class Codec {
	// Encodes a tree to a single string
	public String serialize(TreeNode root) {
		if (root == null) {
			return "N";
		}
		StringBuilder ans = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				ans.append("N,");
			} else {
				ans.append(node.val).append(",");
				q.offer(node.left);
				q.offer(node.right);
			}
		}
		return ans.toString();
	}

	// Decodes the encoded data to tree
	public TreeNode deserialize(String data) {
		String[] vals = data.split(",");
		if (vals[0].equals("N")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		int index = 1;
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (!vals[index].equals("N")) {
				node.left = new TreeNode(Integer.parseInt(vals[index]));
				q.offer(node.left);
			}
			index++;
			if (!vals[index].equals("N")) {
				node.right = new TreeNode(Integer.parseInt(vals[index]));
				q.offer(node.right);
			}
			index++;
		}
		return root;
	}
}
