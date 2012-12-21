package com.hmsonline.storm.osgi.topology;

import backtype.storm.Config;
import com.hmsonline.storm.osgi.bolt.IBoltDefinition;
import com.hmsonline.storm.osgi.spout.ISpoutDefinition;
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
  private List<IBoltDefinition> bolts;
  private List<ISpoutDefinition> spouts;

  /**
   * @return the bolts
   */
  @Override
  public List<IBoltDefinition> getBolts() {
    return bolts;
  }

  /**
   * @param bolts the bolts to set
   */
  @Override
  public void setBolts(List<IBoltDefinition> bolts) {
    this.bolts = bolts;
  }

  /**
   * @return the spouts
   */
  @Override
  public List<ISpoutDefinition> getSpouts() {
    return spouts;
  }

  /**
   * @param spouts the spouts to set
   */
  @Override
  public void setSpouts(List<ISpoutDefinition> spouts) {
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
