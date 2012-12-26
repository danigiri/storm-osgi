package com.hmsonline.storm.osgi.bolt;

import backtype.storm.tuple.Tuple;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rmoquin
 */
public interface IBoltExecutor extends Serializable {
  public List<Object> execute(Tuple tuple);
}
