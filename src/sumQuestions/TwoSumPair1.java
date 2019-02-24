package sumQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumPair1 {
  /**
   * Find all pairs of elements in a given array that sum to the given target number.
   * Return all the pairs of indices.
   *
   * Examples:
   *
   * A = {1, 3, 2, 4} target = 5 return [[0, 3], [1, 2]]
   *
   * A = {1, 2, 2, 4} target = 6 return [[1, 3], [2, 3]]
   *
   * The idea is pretty similar to 2 sum, we could use a HashMap to store the elements we have
   * encountered, in this case, we only need to traverse the input list once. Since the output
   * need to be a index pair, so the HashMap may be a little bit different. The key of this Map
   * is still the element we met, but the value is a list of Integers, which indicates the indices
   * of all the elements with same value.
   */
  public List<List<Integer>> allPairs(int[] array, int target){
    List<List<Integer>> result = new ArrayList<>();
    Map<Integer, List<Integer>> indexMap = new HashMap<>();
    for(int i = 0; i < array.length; i++){
      List<Integer> indices = indexMap.get(target - array[i]);
      if(indices != null){
        for(int j : indices){
          List<Integer> ans = new ArrayList<>();
          ans.add(j);
          ans.add(i);
          result.add(ans);
        }
      }
      if(!indexMap.containsKey(array[i])){
        indexMap.put(array[i], new ArrayList<>());
      }
      indexMap.get(array[i]).add(i);
    }
    return result;
  }

  public static void main(String[] args) {
    TwoSumPair1 TwoSumPair1 = new TwoSumPair1();
    int[] test1 = {1, 3, 2, 4};
    int[] test2 = {1, 2, 2, 4};
    List<List<Integer>> result1 = TwoSumPair1.allPairs(test1, 5);
    for(List<Integer> list : result1){
      for(int i : list){
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
