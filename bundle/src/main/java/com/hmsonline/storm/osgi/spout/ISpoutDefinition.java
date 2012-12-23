package com.hmsonline.storm.osgi.spout;

import backtype.storm.topology.IRichSpout;
import java.util.List;

/**
 *
 * @author rmoquin
 */
public interface ISpoutDefinition extends IRichSpout {  

  /**
   * @return the outputFields
   */
  List<String> getOutputFields();

  /**
   * @return the parallelismHint
   */
  Integer getParallelismHint();

  /**
   * @return the source
   */
  ITupleSource getSource();

  /**
   * @param outputFields the outputFields to set
   */
  void setOutputFields(List<String> outputFields);

  /**
   * @param parallelismHint the parallelismHint to set
   */
  void setParallelismHint(Integer parallelismHint);

  /**
   * @param source the source to set
   */
  void setSource(ITupleSource source);

  /**
   * @return the name
   */
  String getName();

  /**
   * @param name the name to set
   */
  void setName(String name);
}
