package com.hmsonline.storm.osgi.impl;

import backtype.storm.LocalCluster;
import com.hmsonline.storm.osgi.topology.ITopologyDefinition;

/**
 * Represents a constructed topology that is ready to be or is deployed, along with
 * any other objects associated with it's deployment.
 * 
 * @author rmoquin
 */
public class TopologyDeployment {
  private LocalCluster cluster;
  private ITopologyDefinition definition;
  
  public void shutdown() {
    this.cluster.shutdown();
  }
  
  /**
   * @return the definition
   */
  public ITopologyDefinition getDefinition() {
    return definition;
  }

  /**
   * @param definition the definition to set
   */
  public void setDefinition(ITopologyDefinition definition) {
    this.definition = definition;
  }
  
  /**
   * @return the definition
   */
  public LocalCluster getCluster() {
    return cluster;
  }

  /**
   * @param cluster the cluster to set
   */
  public void setCluster(LocalCluster cluster) {
    this.cluster = cluster;
  }
}
