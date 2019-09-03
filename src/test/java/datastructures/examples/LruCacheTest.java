package datastructures.examples;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LruCacheTest {

  private LruCache<String,Integer> lruCache;
  @Before
  public void setUpTestData(){
    lruCache = new LruCache<>(5);
    lruCache.add("Jan",timeIntensiveOperation("Jan"));
    lruCache.add("Feb",timeIntensiveOperation(("Feb")));
    lruCache.add("March",timeIntensiveOperation("March"));
    lruCache.add("April",timeIntensiveOperation("April"));
    lruCache.add("May",timeIntensiveOperation("May"));
  }

  @Test
  public void test_insert_when_lrucache_filled_removes_least_used(){
    lruCache.add("Jan",timeIntensiveOperation("Jan"));
    lruCache.add("June",timeIntensiveOperation("June"));
    Assertions.assertThat(lruCache.get("Feb")).isNull();
  }

  @Test
  public void test_item_moved_to_top_when_exists_in_lrucache(){
    Assertions.assertThat(lruCache.getTopHitKeys(5)).containsExactly("May","April","March","Feb","Jan");
    lruCache.add("March",timeIntensiveOperation("March"));
    Assertions.assertThat(lruCache.getTopHitKeys(5)).containsExactly("March","May","April","Feb","Jan");

  }
  private Integer timeIntensiveOperation(String key){
    if (key.equals(null) || key.equals(""))
      return 0;
    return (int) key.charAt(0);
  }
}
