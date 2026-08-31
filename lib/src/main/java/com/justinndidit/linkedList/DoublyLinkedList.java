package com.justinndidit.linkedList;

public class DoublyLinkedList<T>{
  private Node<T> head;
  private Node<T> tail;
  private int size;


  private static class Node<T>{
    T data;
    Node<T> next;
    Node<T> previous;

    public Node(T data){
      this.data = data;
    }
  }
}