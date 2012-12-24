package com.hmsonline.storm.osgi.bolt;

import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import java.io.Serializable;

/**
 *
 * @author rmoquin
 */
public interface IBoltExecutor extends Serializable {
  public Values execute(Tuple tuple);
}
