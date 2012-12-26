package com.hmsonline.storm.osgi.topology;

/**
 *
 * @author rmoquin
 */
public interface ITopologyComponent {

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
