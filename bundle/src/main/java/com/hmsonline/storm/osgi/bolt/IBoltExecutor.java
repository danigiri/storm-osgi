package com.hmsonline.storm.osgi.bolt;

import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 *
 * @author rmoquin
 */
public interface IBoltExecutor {
  public Values execute(Tuple tuple);
}
