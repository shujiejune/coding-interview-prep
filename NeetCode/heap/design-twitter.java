class Twitter {
	public class TweetNode {
		int id;
		TweetNode next;

		public TweetNode(int id, TweetNode next) {
			this.id = id;
			this.next = next;
		}
	}

	int tweetInd;
	Map<Integer, Integer> tweetMap;  // (tweetId, tweetIndex)
	Map<Integer, List<TweetNode>> userMap;
	Map<Integer, Set<Integer>> followMap;

	public Twitter() {
		tweetInd = 0;
		tweetMap = new HashMap<>();
		userMap = new HashMap<>();
		followMap = new HashMap<>();
	}

	public void postTweet(int userId, int tweetId) {
		tweetMap.put(tweetId, tweetInd);
		if (!userMap.containsKey(userId)) {
			userMap.put(userId, new LinkedList<>());
		}
		userMap.get(userId).add(new TweetNode(tweetId, null));
		tweetInd++;
	}

	public List<Integer> getNewsFeed(int userId) {
		List<Integer> feed = new ArrayList<>();
		Set<Integer> followees = new HashSet<>();
		if (!followMap.containsKey(userId)) {
			followMap.put(userId, followees);
		}
		followees = followMap.get(userId);
		followees.add(userId);
		PriorityQueue<TweetNode> maxHeap = new PriorityQueue<>((a, b) -> tweetMap.get(b.id) - tweetMap.get(a.id));
		for (int f : followees) {
			maxHeap.offer(userMap.get(f).getFirst());
		}
		while (!maxHeap.isEmpty() && feed.size() < 10) {
			TweetNode currTweet = maxHeap.poll();
			feed.add(currTweet.id);
			if (currTweet.next != null) {
				maxHeap.offer(currTweet.next);
			}
		}
		return feed;
	}

	public void follow(int followerId, int followeeId) {
		if (!followMap.containsKey(followerId)) {
			followMap.put(followerId, new HashSet<>());
		}
		followMap.get(followerId).add(followeeId);
	}

	public void unfollow(int followerId, int followeeId) {
		Set<Integer> followees = followMap.get(followerId);
		if (followees.contains(followeeId)) {
			followees.remove(followeeId);
		}
	}
}
