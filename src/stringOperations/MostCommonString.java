package stringOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MostCommonString {
  /**
   * Given a paragraph and a list of banned words, return the most frequent word that is not in the
   * list of banned words. It is guaranteed there is at least one word that isn't banned, and that
   * the answer is unique.
   *
   * Words in the list of banned words are given in lowercase, and free of punctuation.
   * Words in the paragraph are not case sensitive. The answer is in lowercase.
   */

  public String mostCommonWord(String paragraph, String[] banned){
    HashMap<String, Integer> wordCount = new HashMap<>();
    Set<String> ban = new HashSet<>(Arrays.asList(banned));
    String[] splited = paragraph.replaceAll("\\pP", " ").split("\\s+");
    int maxCount = 0;
    String result = null;
    for(String str : splited){
      str = str.toLowerCase();
      if(!ban.contains(str)){
        wordCount.put(str, wordCount.getOrDefault(str, 0) + 1);
        if(wordCount.get(str) > maxCount){
          result = str;
          maxCount = wordCount.get(str);
        }
      }

    }
    return result;
  }

  public static void main(String[] args) {
    String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String[] banned = {"hit"};
    MostCommonString mostCommonString = new MostCommonString();
    System.out.println(mostCommonString.mostCommonWord(paragraph, banned));

  }
}
