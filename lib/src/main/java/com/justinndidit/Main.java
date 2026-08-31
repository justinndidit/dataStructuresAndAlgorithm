package com.justinndidit;

import com.justinndidit.linkedList.SinglyLinkedList;

public class Main{
  public static void main(String[] args) {
    System.out.println("DSA!!!!");
    SinglyLinkedList<String> list = new SinglyLinkedList<>();
    System.out.println(list.toString());
    list.add("Favour");
    list.add("Anuoluwapo");
    System.out.println(list.toString());
    list.insert(0, "Olasoji");
    list.addFirst("Surgee");
    System.out.println(list.toString());
    list.remove("Surgee");
    System.out.println(list.toString());
    System.out.println(list.isEmpty());
    System.out.println(list.size());
    System.out.println(list.get(0));
    System.out.println(list.contains("Surgee"));
  }
}