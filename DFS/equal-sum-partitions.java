/**
An equal sum partition of a sequence of numbers is a grouping of the numbers (in the same order as the original sequence) in such a way that each group has the same sum. For example, the sequence: 

2 5 1 3 3 7 

may be grouped as: 

(2 5) (1 3 3) (7) 

to yield an equal sum of 7. 


Note: The partition that puts all the numbers in a single group is an equal sum partition with the sum equal to the sum of all the numbers in the sequence. 


For this problem, you will write a program that takes as input a sequence of positive integers and returns the smallest sum for an equal sum partition of the sequence. 


Input: A decimal integer N (1 ≤ N ≤ 10000), giving the total number of integers in the array, and the array X of positive decimal integers.


Output: the smallest sum for an equal sum partition of the sequence.
*/
public class Solution {
  public int getMinEqualSumPartition(int N, int[] X) {
    // Write your solution here
    int sum = 0; 
    for (int i = 0; i < N; i++) {
      sum += X[i];
    }
    for (int i = 1; i < sum; i++) {
      if (partition(X, N, i)) {
        return i;
      }
    }
    return sum;
  }

  private boolean partition(int[] X, int N, int partSum) {
    int currSum = 0;
    for (int i = 0; i < N; i++) {
      currSum += X[i];
      if (currSum == partSum) {
        currSum = 0;
      } else if (currSum > partSum) {
        return false;
      }
    }
    if (currSum != 0 && currSum != partSum) {
      return false;
    }
    return true;
  }
}


/* Given an array of integers, how to divide it into 2 arrays, 
 * with their sum equal to each other.
 */
public List<List<Integer>> equalSum(int[] array) {
  List<List<Integer>> ans = new ArrayList<>();
  int sum = 0;
  for (int num : array) {
    sum += num;
  }
  List<Integer> indices = new ArrayList<>();
  dfs(array, 0, sum / 2, new ArrayList<>(), part1);
  List<Integer> part1 = new ArrayList<>();
  List<Integer> part2 = new ArrayList<>();
  int curr = 0;
  for (int i = 0; i < array.length; i++) {
    if (curr < indices.size() && i == indices.get(curr)) {
      part1.add(array[i]);
      curr++;
    } else {
      part2.add(array[i]);
    }
  }
  ans.add(part1);
  ans.add(part2);
  return ans;
}

private void dfs(int[] array, int index, int target, List<Integer> part, List<Integer> indices) {
  if (target == 0) {
    indices = new ArrayList<>(part);
    return;
  }
  if (index == array.length) {
    return;
  }
  part.add(index);
  dfs(array, index + 1, target - array[index], part, ans);
  part.remove(part.size() - 1);
  dfs(array, index + 1, target, part, ans);
}
