package com.hmsonline.storm.starter.executor;

import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.hmsonline.storm.osgi.bolt.IBoltExecutor;
import java.util.HashMap;
import java.util.Map;

/**
 * An IBoltExecutor implementation which accepts tuples containing a single word, it then proceeds to count the number of duplicate words.
 *
 * @org.apache.xbean.XBean element="wordCounter" description="Provides word counting functionality for a Storm Bolt."
 *
 * @author rmoquin
 */
public class WordCounter implements IBoltExecutor {

  private Map<String, Integer> counts = new HashMap<String, Integer>();

  @Override
  public Values execute(Tuple tuple) {
    String word = tuple.getString(0);
    Integer count = counts.get(word);
    if (count == null) {
      count = 0;
    }
    count++;
    counts.put(word, count);
    return new Values(word, count);
  }
}
