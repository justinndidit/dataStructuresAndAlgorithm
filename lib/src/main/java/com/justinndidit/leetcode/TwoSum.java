package com.justinndidit.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  public static int[] twoSum(int[] nums, int target){

    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      int difference = target - nums[i];
      if(seen.get(difference) != null) return new int[]{seen.get(difference), i};
      seen.put(nums[i], i);
    }
    return new int[]{-1,-1};
  }
}
