package sumQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FourSum {
  /**
   * Determine if there exists a set of four elements in a given array that sum to the given target
   */

  // method 1: sort the array first O(n ^ 3)
  public boolean exist(int[] array, int target){
    Arrays.sort(array);
    for(int i = 0; i < array.length - 3; i++){
      for(int j = i + 1; j < array.length - 2; j++){
        int left = j + 1;
        int right = array.length - 1;
        int curTarget = target - array[i] - array[j];
        while(left < right){
          int sum = array[left] + array[right];
          if(sum == curTarget){
            return true;
          } else if(sum < curTarget){
            left++;
          } else {
            right--;
          }
        }
      }
    }
    return false;
  }

  // method2: get all pairs of numbers and apply 2 sum O(n ^ 2 * log n)
  class Element implements Comparable<Element>{
    int left;
    int right;
    int sum;

    Element(int left, int right, int sum){
      this.left = left;
      this.right = right;
      this.sum = sum;
    }

    @Override
    public int compareTo(Element another){
      if(this.sum != another.sum){
        return this.sum < another.sum ? -1 : 1;
      } else if(this.right != another.right){
        return this.right < another.right ? -1 : 1;
      } else if(this.left != another.left){
        return this.left < another.left ? -1 : 1;
      }
      return 0;
    }
  }

  public boolean existII(int[] array, int target){
    // we can split the tuples into two pair elements: (i, j) and (k ,l)
    // so that array[i] + array[j] <= array[k] + array[l]
    Arrays.sort(array);
    Element[] pairSum = getPairSum(array);
    Arrays.sort(pairSum);
    int left = 0;
    int right = pairSum.length - 1;
    while(left < right){
      if(pairSum[left].sum + pairSum[right].sum == target && pairSum[left].right < pairSum[right].left){
        return true;
      } else if(pairSum[left].sum + pairSum[right].sum < target){
        left++;
      } else {
        right--;
      }
    }
    return false;
  }

  private Element[] getPairSum(int[] array){
    Element[] pairSum = new Element[array.length * (array.length - 1) / 2];
    int curIndex = 0;
    for(int i = 1; i < array.length; i++){
      for(int j = 0; j < i; j++){
        pairSum[curIndex++] = new Element(j, i, array[i] + array[j]);
      }
    }
    return pairSum;
  }

  // Method3: HashMap O(n ^ 2)
  class Pair{
    int left;
    int right;
    Pair(int left, int right){
      this.left =left;
      this.right = right;
    }
  }

  public boolean existIII(int[] array, int target){
    Map<Integer, Pair> map = new HashMap<>();
    for(int i = 1; i < array.length; i++){
      for(int j = 0; j < i; j++){
        int pairSum = array[j] + array[i];
        if(map.containsKey(target - pairSum) && map.get(target - pairSum).right < j){
          return true;
        }
        // we only need to store the pair with smallest right index
        if(!map.containsKey(pairSum)){
          map.put(pairSum, new Pair(j, i));
        }
      }
    }
    return false;
  }
}
