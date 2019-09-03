package datastructures.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LruCache<K, V> {

  private Map<K, Node> lru;
  private Node lruHead;
  private Node lruTail;
  private int size;
  private int max_capacity;

  public boolean add(K key, V value) {
    if (!lru.containsKey(key)) {
      if (this.size + 1 > max_capacity) {
        // remove the element from the lruTail
        this.removeLeastUsedItem();
      }
      // simply add to lruHead
      Node<K, V> newItem = new LruCache.Node<>(key, value);
      this.addToHead(newItem);
      this.lru.put(key, newItem);
      this.size++;

    } else {
      Node<K, V> previouslyCachedItem = lru.get(key);
      // remove the element from the list
      if (previouslyCachedItem.prev != null && previouslyCachedItem.next != null) {
        previouslyCachedItem.prev.next = previouslyCachedItem.next;
        previouslyCachedItem.next.prev = previouslyCachedItem.prev;
      } else if (previouslyCachedItem.prev != null && previouslyCachedItem.next == null) {
        // cached item is at the end of the list and now is being moved to the top. Here we need update the lruTail & lruHead
        this.lruTail = previouslyCachedItem.prev;
        previouslyCachedItem.prev.next = null;
      } else {
        // do nothing since the cachedItem is already at the top of list
      }

      this.addToHead(previouslyCachedItem);
    }
    return true;
  }

  /**
   * @param n
   * @return the top N hit keys, where N is Math.min (n,size)
   * @throws IllegalArgumentException if n is greater than max_capacity
   */
  public List<K> getTopHitKeys(int n) {
    if (n > max_capacity) {
      throw new IllegalArgumentException(String.format("Maximum keys stored in lru cache is {}", this.max_capacity));
    }
    n = Math.min(n, size);

    List<K> topKeys = new ArrayList<>(n);
    Node<K, V> tp = this.lruHead;
    while (n > 0) {
      topKeys.add(tp.key);
      tp = tp.next;
      n--;
    }
    return topKeys;
  }

  public V get(K key) {

    return lru.get(key) != null ? (V) lru.get(key).value : null;
  }

  public LruCache(int capacity) {
    this.max_capacity = capacity;
    this.lru = new HashMap<>(capacity);
  }

  private static class Node<K, V> {
    private K key;
    private V value;

    private Node<K, V> next;
    private Node<K, V> prev;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public Node(K key, V value, Node<K, V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

  }

  private void addToHead(Node<K, V> newItem) {
    if (this.lruHead == null) {
      this.lruHead = newItem;
      this.lruTail = newItem;
    } else {

      newItem.prev = null;
      newItem.next = this.lruHead;
      this.lruHead.prev = newItem;
      this.lruHead = newItem;
    }
  }

  private void removeLeastUsedItem() {
    this.lru.remove(lruTail.key);
    this.lruTail = lruTail.prev;
    this.lruTail.next = null;
    this.size--;
  }


}
