package com.justinndidit.hashTable;

import java.util.Objects;

import com.justinndidit.linkedList.SinglyLinkedList;

public class HashTableWithLinkedList<K,V> {
  private static final int BUCKET_CAPACITY = 16; //DEFAULT SIZE OF Bucket
  private SinglyLinkedList<Entry<K,V>>[] bucket; //BUCKET: ARRAY OF LINKED LISTS
  private int size;

  private static class Entry<K,V>{
    K key;
    V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  //CONSTRUCTOR METHOD
  @SuppressWarnings("unchecked")
  public HashTableWithLinkedList(){
    this.size = 0;
    this.bucket = new SinglyLinkedList[BUCKET_CAPACITY];
  }

  public void put(K key, V value){
    if(Objects.isNull(key)) throw new IllegalArgumentException();

    int index = getBucketIndex(key);
    if(index < 0 || index >= this.bucket.length) throw new IndexOutOfBoundsException();

    if(this.bucket[index] == null) this.bucket[index] = new SinglyLinkedList<>();

    for(int i = 0; i < this.bucket[index].size(); i++){
      var node = this.bucket[index].get(i);
      if(Objects.equals(node.key, key)){
        node.value = value;
        return;
      }
    }
    bucket[index].add(new Entry<>(key, value));
    this.size++;
  }

  public V get(K key){
    if(Objects.isNull(key)) throw new IllegalArgumentException();

    int index = getBucketIndex(key);
    if(index < 0 || index >= this.bucket.length) throw new IndexOutOfBoundsException();

    if(bucket[index] == null) return null;

    for(int i = 0; i < bucket[index].size(); i++) {
      var node = bucket[index].get(i);
      if(Objects.equals(key, node.key)) return node.value;
    }
    return null;
  }

  public boolean remove(K key){
    if(Objects.isNull(key)) throw new IllegalArgumentException();

    int index = getBucketIndex(key);
    if(index < 0 || index >= this.bucket.length) throw new IndexOutOfBoundsException();

    if(bucket[index] == null) return false;

    for(int i =0 ; i < bucket[index].size(); i++){
      var node = bucket[index].get(i);
      if(Objects.equals(key, node.key)) {
        bucket[index].remove(node);
        return true;
      }
    }
    return false;
  }

  public boolean containsKey(K key){
    if(Objects.isNull(key)) throw new IllegalArgumentException();

    int index = getBucketIndex(key);
    if(index < 0 || index >= this.bucket.length) throw new IndexOutOfBoundsException();

    if(bucket[index] == null) return false;

    for(int i =0 ; i < bucket[index].size(); i++){
      var node = bucket[index].get(i);
      if(Objects.equals(key, node.key)) return true;
    }
    return false;
  }

  public int size(){
    return  this.size;
  }

  @Override
  public String toString(){
    return "";
  }

  //HELPER METHODS
  private int getBucketIndex(K key) {
    if(Objects.isNull(key)) throw new IllegalArgumentException();

    int hash = key.hashCode();
    return Math.abs(hash) % BUCKET_CAPACITY;
  }
}