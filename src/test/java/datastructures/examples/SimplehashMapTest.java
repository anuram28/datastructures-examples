package datastructures.examples;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class SimplehashMapTest {

  @Test
  public void testSimpleHashMap() {

    SimpleHashMap<String> shp = new SimpleHashMap<>();
    shp.put("foo", "bar");
    shp.put("foo", "bar");
    shp.put("fizz", "buzz");
    shp.put("FIZZ", "FIZZBUZZ");
    shp.put("FIZZ", "FIZZ");
    shp.put("duplicate", "FIZZ");

    assertThat(shp.get("foo")).isEqualTo("bar");
    assertThat(shp.get("fizz")).isEqualTo("buzz");
    assertThat(shp.values()).containsExactlyInAnyOrder("bar", "buzz", "FIZZ", "FIZZ");
    assertThat(shp.keySet()).containsOnlyOnce("foo", "fizz", "FIZZ", "duplicate");
    assertThat(shp.containsKey("random")).isFalse();
    assertThat(shp.size()).isEqualTo(4);
    shp.remove("fizz");
    assertThat(shp.size()).isEqualTo(3);
    assertThat(shp.containsKey("fizz")).isFalse();


  }
}
