package datastructures.examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleHashMap<V> {

  private int capacity = 26;
  private Entry<String, V>[] buckets;
  private int counter = 0;

  public SimpleHashMap() {
    this.buckets = (Entry<String, V>[]) new Entry<?, ?>[capacity];
  }

  public SimpleHashMap(int capacity) {
    this.capacity = capacity;
    this.buckets = (Entry<String, V>[]) new Entry[capacity];
  }

  public int put(String key, V value) {
    // find the bucket index using hashing for key
    int bucketIndex = hashingFunction(key);
    Entry<String, V> firstEntry = buckets[bucketIndex];

    if (firstEntry == null) {
      firstEntry = new Entry<>(key, value);
      buckets[bucketIndex] = firstEntry;
    } else {

      Entry<String, V> prev = null;
      Entry<String, V> tp = firstEntry;
      while (tp != null) {
        if (tp.getKey().equals(key)) {
          tp.setValue(value);
          return 1;
        }
        prev = tp;
        tp = tp.getNext();
      }
      prev.setNext(new Entry<>(key, value));

    }
    return 1;
  }

  public V get(String key) {
    // find the bucket index using hashing for key
    int bucketIndex = hashingFunction(key);
    Entry<String, V> firstEntry = buckets[bucketIndex];
    Entry<String, V> tp = firstEntry;
    while (tp != null) {
      if (tp.getKey().equals(key)) {
        return tp.getValue();
      }
      tp = tp.getNext();
    }
    return null;
  }


  public boolean containsKey(String key) {
    int bucketIndex = hashingFunction(key);
    Entry<String, V> firstEntry = buckets[bucketIndex];

    while (firstEntry != null) {
      if (firstEntry.getKey().equals(key)) {
        return true;
      }
      firstEntry = firstEntry.getNext();
    }
    return false;
  }

  public Set<String> keySet() {
    Set<String> keySet = new HashSet<>();

    for (Entry<String, V> entry : buckets) {

      while (entry != null) {
        keySet.add(entry.getKey());
        entry = entry.getNext();
      }
    }
    return keySet;
  }

  public List<V> values() {
    List<V> values = new ArrayList<>();
    for (Entry<String, V> entry : buckets) {
      while (entry != null) {
        values.add(entry.getValue());
        entry = entry.getNext();
      }
    }
    return values;
  }

  private int hashingFunction(String key) {
    int asciiVal = (int) key.toUpperCase().charAt(0);
    return asciiVal - 65;
  }
}
