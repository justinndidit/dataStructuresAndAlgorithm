package com.justinndidit.linkedList;

import java.util.Objects;

public class SinglyLinkedList<T>{
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public static class Node<T>{
    T data;
    Node<T> next;

    public Node(T data){
      this.data = data;
    }
  }

  public SinglyLinkedList(){
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public void add(T data){
    if(Objects.isNull(data)) throw new IllegalArgumentException();

    var newNode = new Node<T>(data);
    if(this.head == null){
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail.next = newNode;
      this.tail = newNode;
    }

    this.size++;
  }

  public void addFirst(T data){
    if(Objects.isNull(data)) throw new IllegalArgumentException();

    var newNode = new Node<T>(data);
    if(this.head == null) {
      this.head = newNode;
      this.tail = newNode;
    } else{
      newNode.next = this.head;
      this.head = newNode;
    }
    this.size++;
  }

  public void insert(int index, T data){
    if(Objects.isNull(data)) throw new IllegalArgumentException();
    if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

    if(index == 0) {
      this.addFirst(data);
      return;
    }

    var newNode = new Node<T>(data);
    var current = this.head;
    for(int i = 0; i < index; i++) {
      current = current.next;
    }

    newNode.next = current.next;
    current.next = newNode;
    if(newNode.next == null) this.tail = newNode;
    this.size++;
  }

  public boolean remove(T data){
    if(Objects.isNull(data)) throw new IllegalArgumentException();
    if (this.head == null) return false;

    if (Objects.equals(this.head.data, data)){
      this.head = this.head.next;
      if (this.head == null) this.tail = null;
      this.size--;
      return true;
    }

    var current = this.head;
    while (current.next != null) {
      if(Objects.equals(current.next.data, data)){
        current.next = current.next.next;
        if(current.next == null) this.tail = current;
        this.size--;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public T get(int index){
    if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();

    var current = this.head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    return current.data;
  }

  public int size(){
    return this.size;
  }

  public boolean isEmpty(){
    return this.size == 0;
  }

  public boolean contains(T data){
    if(Objects.isNull(data)) throw new IllegalArgumentException();

    var current = this.head;

    while(current != null){
      if(Objects.equals(current.data, data))return true;

      current = current.next;
    }
    return false;
  }

  @Override
  public String toString(){
    if(this.size == 0) return "linked list is empty";
    StringBuilder sb = new StringBuilder();

    var current = this.head;

    while(current != null) {
      String str = "[" + current.data + "]";
      sb.append(str);
      if(current.next != null) sb.append("->");
      current = current.next;
    }

    return sb.toString();
  }

}