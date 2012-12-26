package com.hmsonline.storm.osgi.impl;

import backtype.storm.LocalCluster;
import backtype.storm.Config;
import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.TopologyBuilder;
import clojure.osgi.IClojureLoader;
import com.hmsonline.storm.osgi.bolt.BoltDefinition;
import com.hmsonline.storm.osgi.spout.SpoutDefinition;
import com.hmsonline.storm.osgi.subscription.DistributionPolicy;
import com.hmsonline.storm.osgi.topology.ITopologyDefinition;
import com.hmsonline.storm.osgi.topology.Subscription;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rmoquin
 */
public class TopologyManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(TopologyManager.class);
  private IClojureLoader clojureLoader;
  private ConcurrentMap<String, TopologyDeployment> topologyMap = new ConcurrentHashMap<String, TopologyDeployment>();

  public void onBindService(ITopologyDefinition definition) {
    try {
      this.submitTopology(definition);
    } catch (Exception ex) {
      LOGGER.error("Error binding topology service.", ex);
    }
  }

  public void onUnbindService(ITopologyDefinition definition) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Topology service, " + definition.getName() + " is being undeployed, shutting down topology if currently deployed.");
    }
    TopologyDeployment deployment = this.topologyMap.get(definition.getName());
    if (deployment != null) {
      deployment.shutdown();
    } else {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn("Attempted to shutdown topology cluster, " + definition.getName() + ", but none exists with that name for undeployed Topology Service.");
      }
    }
  }

  public void submitTopology(ITopologyDefinition definition) throws Exception {
    TopologyDeployment td = new TopologyDeployment();
    TopologyDeployment currentTopology = this.topologyMap.putIfAbsent(definition.getName(), td);
    if (currentTopology != null) {
      throw new IllegalStateException("A topology with the name, " + definition.getName() + " has already been deployed, request for deployment will be ignored.");
    } else {
      TopologyBuilder builder = new TopologyBuilder();
      List<SpoutDefinition> spouts = definition.getSpouts();
      for (Iterator<SpoutDefinition> it = spouts.iterator(); it.hasNext();) {
        SpoutDefinition spoutDef = it.next();
        builder.setSpout(spoutDef.getName(), spoutDef, spoutDef.getParallelismHint());
      }
      List<BoltDefinition> bolts = definition.getBolts();
      for (Iterator<BoltDefinition> it = bolts.iterator(); it.hasNext();) {
        BoltDefinition boltDef = it.next();
        BoltDeclarer declarer = builder.setBolt(boltDef.getName(), boltDef, boltDef.getParallelismHint());
        Subscription subscription = boltDef.getSubscription();
        DistributionPolicy distribution = subscription.getDistribution();
        distribution.setup(declarer, subscription);
      }

      Config conf = new Config();
      conf.setDebug(true);
      /*    if (name != null) {
       conf.setNumWorkers(3);
       StormSubmitter.submitTopology(name, conf, builder.createTopology());
       } else {*/
      conf.setMaxTaskParallelism(3);
      LocalCluster cluster = (LocalCluster) this.clojureLoader.createInstance(this, "backtype.storm.LocalCluster");
      cluster.submitTopology(definition.getName(), conf, builder.createTopology());
      td.setCluster(cluster);
      td.setDefinition(definition);
    }
    //}
  }

  /**
   * @return the clojureLoader
   */
  public IClojureLoader getClojureLoader() {
    return clojureLoader;
  }

  /**
   * @param clojureLoader the clojureLoader to set
   */
  public void setClojureLoader(IClojureLoader clojureLoader) {
    this.clojureLoader = clojureLoader;
  }
}
