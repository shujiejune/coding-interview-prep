public class MedianFinder {
  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;
  // minHeap.peek() >= maxHeap.peek()
  // Math.abs(minHeap.size() - maxHeap.size()) <= 1

  public MedianFinder () {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  }

  public void addNum(int num) {
    if (minHeap.size() == maxHeap.size()) {
      if (minHeap.size() == 0) {
        minHeap.offer(num);
      } else if (maxHeap.peek() < num) {
        minHeap.offer(num);
      } else {
        maxHeap.offer(num);
      }
    } else if (minHeap.size() == 0 && !maxHeap.isEmpty()) {
      int left = maxHeap.poll();
      maxHeap.offer(Math.min(left, num));
      minHeap.offer(Math.max(left, num));
    } else if (!minHeap.isEmpty() && maxHeap.size() == 0) {
      int right = minHeap.poll();
      maxHeap.offer(Math.min(right, num));
      minHeap.offer(Math.max(right, num));
    } else if (minHeap.size() > maxHeap.size()) {
      int right = minHeap.poll();
      int left = maxHeap.peek(); // left <= right
      if (num <= right) {
        maxHeap.offer(num);
        minHeap.offer(right);
      } else {
        maxHeap.offer(Math.min(right, num));
        minHeap.offer(Math.max(right, num));
      }
    } else {
      int right = minHeap.peek();
      int left = maxHeap.poll(); // left <= right
      if (num >= left) {
        minHeap.offer(num);
        maxHeap.offer(left);
      } else {
        maxHeap.offer(Math.min(left, num));
        minHeap.offer(Math.max(left, num));
      }
    }
  }

  public double findMedian() {
    if (minHeap.size() == maxHeap.size()) {
      return (minHeap.peek() + maxHeap.peek()) / 2.0;
    } else if (minHeap.size() > maxHeap.size()) {
      return (double) minHeap.peek();
    } else {
      return (double) maxHeap.peek();
    }
  }
}

// TC: O(mlogn) for addNum(), O(m) for findMedian()
// SC: O(n)
// Where m is the number of function calls, n is the length of the data stream
