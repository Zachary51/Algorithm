package sumQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
  /**
   * Determine if there exists three elements in a given array that sum to the given target number.
   * Return all the triple of values taht sums to target.
   *
   * Examples:
   * A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4],[2, 2, 4]]
   */
  public List<List<Integer>> allTriples(int[] array, int target){
    // Assumption: array is not null and array.length >= 3
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(array);
    for(int i = 0; i < array.length - 2; i++){
      // to find i < j < k, such that array[i] + array[j] + array[k] == target
      // to make sure that there is no duplicate tuple, we ignore all the duplicate possible i
      // e.g. if we have 2, 2, 2, only the first 2 will be selected
      if(i > 0 && array[i] == array[i - 1]){
        continue;
      }
      int left = i + 1;
      int right = array.length - 1;
      while(left < right){
        int tempSum = array[left] + array[right];
        if(tempSum + array[i] == target){
          result.add(Arrays.asList(array[i], array[left], array[right]));
          left++;
          // ignore all the possible duplicate j as well
          while(left < right && array[left] == array[left - 1]){
            left++;
          }
        } else if(tempSum + array[i] < target){
          left++;
        } else {
          right--;
        }
      }
    }
    return result;

  }

  public List<List<Integer>> threeSum(int[] array, int target){
    Set<List<Integer>> res = new HashSet<>();
    if(array.length == 0){
      return new ArrayList<>(res);
    }
    Arrays.sort(array);
    for(int i = 0; i < array.length - 1; i++){
      int j = i + 1;
      int k = array.length - 1;
      while(j < k){
        int currSum = array[i] + array[j] + array[k];
        if(currSum == target){
          res.add(Arrays.asList(array[i], array[j++], array[k--]));
        } else if(currSum > target){
          k--;
        } else if(currSum < target){
          j++;
        }
      }
    }
    return new ArrayList<>(res);
  }

  public static void main(String[] args) {
    ThreeSum threeSum = new ThreeSum();
    int[] input = {2, 2, 4, 4, 2, 2};
    List<List<Integer>> result = threeSum.allTriples(input, 8);
    for(List<Integer> list : result){
      for(int i : list){
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

}
