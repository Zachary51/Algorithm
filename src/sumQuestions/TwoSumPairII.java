package sumQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumPairII {
  /**
   * Find all pairs of elements in a given array that sum to the pair the given target number.
   * Return all the distinct pair of values;
   *
   * Examples:
   * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
   * In this case, we don't return the indices, but the distinct pair of values, so we could use
   * sort even though it may change the array, but the order doesn't matter. Or we can use HashSet.
   */


  // Method1: sort the array first and use two pointers
  public List<List<Integer>> allPairs(int[] array, int target){
    Arrays.sort(array);
    List<List<Integer>> result = new ArrayList<>();
    int left = 0;
    int right = array.length - 1;
    while(left < right){
      // ignore all the consecutive duplicate values when we want to determine the smaller
      // element of the pair
      if(left > 0 && array[left] == array[left - 1]){
        left++;
        continue;
      }
      int curSum = array[left] + array[right];
      if(curSum == target){
        result.add(Arrays.asList(array[left], array[right]));
      } else if(curSum < target){
        left++;
      } else {
        right--;
      }
    }
    return result;
  }

  // Method2: use HashSet

  public List<List<Integer>> allPairsII(int[] array, int target){
    // Assumptions: array is not null and array.length >= 2
    List<List<Integer>> result = new ArrayList<>();
    // Record the number of existence of the values
    Map<Integer, Integer> map = new HashMap<>();
    for(int num : array){
      // case 1: if 2 * x == target, we need to make sure there is no duplicate (count == 1)
      // case 2: if x + y == target, and this is the first time both x and y are present,
      // so we can make sure there is no duplicates (count == null)
      Integer count = map.get(num);
      if(num * 2 == target && count != null && count == 1){
        result.add(Arrays.asList(num, num));
      } else if(map.containsKey(target - num) && count == null){
        result.add(Arrays.asList(target - num, num));
      }
      if(count == null){
        map.put(num, 1);
      } else {
        map.put(num, count + 1);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    TwoSumPairII twoSumPairII = new TwoSumPairII();
    int[] test1 = {2, 1, 3, 2, 4, 3, 4, 2};
    List<List<Integer>> result = twoSumPairII.allPairsII(test1, 6);
    for(List<Integer> list : result){
      for(int i : list){
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
