package datastructures.examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleHashMap<V> {

  private int capacity = 26;
  private Node<String, V>[] buckets;
  private int counter = 0;

  public SimpleHashMap() {
    this.buckets = (Node<String, V>[]) new Node<?, ?>[capacity];
  }

  public SimpleHashMap(int capacity) {
    this.capacity = capacity;
    this.buckets = (Node<String, V>[]) new Node[capacity];
  }

  public int put(String key, V value) {
    // find the bucket index using hashing for key
    int bucketIndex = hashingFunction(key);
    Node<String, V> firstNode = buckets[bucketIndex];

    if (firstNode == null) {
      firstNode = new Node<>(key, value);
      buckets[bucketIndex] = firstNode;
      this.counter++;
    } else {

      Node<String, V> prev = null;
      Node<String, V> tp = firstNode;
      while (tp != null) {
        if (tp.key.equals(key)) {
          tp.value =value;
          return 1;
        }
        prev = tp;
        tp = tp.next;
      }
      prev.next = new Node<>(key, value);
      this.counter++;

    }
    return 1;
  }

  public V get(String key) {
    // find the bucket index using hashing for key
    int bucketIndex = hashingFunction(key);
    Node<String, V> firstNode = buckets[bucketIndex];
    Node<String, V> tp = firstNode;
    while (tp != null) {
      if (tp.key.equals(key)) {
        return tp.value;
      }
      tp = tp.next;
    }
    return null;
  }


  public boolean containsKey(String key) {
    int bucketIndex = hashingFunction(key);
    Node<String, V> firstNode = buckets[bucketIndex];

    while (firstNode != null) {
      if (firstNode.key.equals(key)) {
        return true;
      }
      firstNode = firstNode.next;
    }
    return false;
  }

  public Set<String> keySet() {
    Set<String> keySet = new HashSet<>();

    for (Node<String, V> node : buckets) {

      while (node != null) {
        keySet.add(node.key);
        node = node.next;
      }
    }
    return keySet;
  }

  public List<V> values() {
    List<V> values = new ArrayList<>();
    for (Node<String, V> node : buckets) {
      while (node != null) {
        values.add(node.value);
        node = node.next;
      }
    }
    return values;
  }

  public boolean remove(String key) {
    int bucketIndex = hashingFunction(key);
    Node tp = buckets[bucketIndex];

    Node prev = null;
    while (tp != null) {
      if (tp.key.equals(key)) {
        if (prev != null) {
          prev.next = tp.next;
        }
        counter--;
        return true;
      } else {
        prev = tp;
        tp = tp.next;
      }
    }
    return false;

  }

  public int size() {
    return this.counter;
  }

  private int hashingFunction(String key) {
    int asciiVal = (int) key.toUpperCase().charAt(0);
    return asciiVal - 65;
  }

  private static class Node<K,V> {
    private K key;
    private V value;
    private Node<K,V> next;

    private Node(K key, V value){
      this.key = key;
      this.value = value;
    }

  }

}
