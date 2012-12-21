package com.hmsonline.storm.osgi.bolt;

import backtype.storm.topology.IBasicBolt;

/**
 *
 * @author rmoquin
 */
public interface IBoltDefinition extends IBasicBolt {

  /**
   * @return the name
   */
  String getName();

  /**
   * @param name the name to set
   */
  void setName(String name);

  /**
   * @return the parallelismHint
   */
  Integer getParallelismHint();

  /**
   * @param parallelismHint the parallelismHint to set
   */
  void setParallelismHint(Integer parallelismHint);
  
}
