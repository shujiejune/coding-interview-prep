class Solution {
    public int solution(int[] numbers, int k) {
        int n = numbers.length;
        int totalCount = 0;
        int right = 0;
        int currentPairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        // Iterate through all possible start points of a subarray
        for (int left = 0; left < n; left++) {
            // Expand the window to the right until we have at least k pairs
            while (right < n && currentPairs < k) {
                int num = numbers[right];
                int oldCount = freq.getOrDefault(num, 0);
                freq.put(num, oldCount + 1);
                
                // If the new count is even, we formed a new pair
                if ((oldCount + 1) % 2 == 0) {
                    currentPairs++;
                }
                right++;
            }

            // If we found a window with at least k pairs...
            if (currentPairs >= k) {
                // ...then all subarrays starting at 'left' and ending at 'right-1' or later are valid.
                // The number of such subarrays is n - (right - 1).
                totalCount += n - (right - 1);
            }

            // Shrink the window from the left for the next iteration
            int numToRemove = numbers[left];
            int oldLeftCount = freq.get(numToRemove);
            freq.put(numToRemove, oldLeftCount - 1);
            
            // If the old count was even, we are breaking a pair
            if (oldLeftCount % 2 == 0) {
                currentPairs--;
            }
        }

        return totalCount;
    }
}
