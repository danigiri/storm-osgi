package com.hmsonline.storm.osgi.topology;

import backtype.storm.Config;
import com.hmsonline.storm.osgi.bolt.BoltDefinition;
import com.hmsonline.storm.osgi.spout.SpoutDefinition;
import java.util.List;

/**
 * An owner POJO used for testing out nested properties
 * 
 * @org.apache.xbean.XBean element="topology" description="Represents the definition of a Storm topology"
 *
 * @author rmoquin
 */
public class TopologyDefinition implements ITopologyDefinition {
  private String name;
  private Config config;
  private List<BoltDefinition> bolts;
  private List<SpoutDefinition> spouts;

  /**
   * @return the bolts
   */
  @Override
  public List<BoltDefinition> getBolts() {
    return bolts;
  }

  /**
   * @param bolts the bolts to set
   */
  @Override
  public void setBolts(List<BoltDefinition> bolts) {
    this.bolts = bolts;
  }

  /**
   * @return the spouts
   */
  @Override
  public List<SpoutDefinition> getSpouts() {
    return spouts;
  }

  /**
   * @param spouts the spouts to set
   */
  @Override
  public void setSpouts(List<SpoutDefinition> spouts) {
    this.spouts = spouts;
  }

  /**
   * @return the name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the config
   */
  @Override
  public Config getConfig() {
    return config;
  }

  /**
   * @param config the config to set
   */
  @Override
  public void setConfig(Config config) {
    this.config = config;
  }
}
