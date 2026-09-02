package com.justinndidit.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GroupAnagram{
  public static List<List<String>> GroupAnagramSort(String[] strs){
    Map<String, List<String>> subStrs = new HashMap<>();

    for(String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String sortedString = new String(chars);

      if(subStrs.get(sortedString) == null) subStrs.put(sortedString, new ArrayList<>());
      subStrs.get(sortedString).add(str);
    }

    return new ArrayList<>(subStrs.values());
  }

  public static List<List<String>> GroupAnagramCount(String[] strs){
    Map<String, List<String>> group = new HashMap<>();

    for(String str : strs) {
      char[] charCount = new char[26];
      for(char c : str.toCharArray()) charCount[c - 'a']++;
      String sortedString = new String(charCount);

      if(group.get(sortedString) == null) group.put(sortedString, new ArrayList<>());
      group.get(sortedString).add(str);
    }

    return new ArrayList<>(group.values());
  }
}