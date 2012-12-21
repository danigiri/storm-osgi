package com.hmsonline.storm.osgi.impl;

import backtype.storm.LocalCluster;
import backtype.storm.Config;
import backtype.storm.topology.TopologyBuilder;
import clojure.osgi.IClojureLoader;
import com.hmsonline.storm.osgi.bolt.IBoltDefinition;
import com.hmsonline.storm.osgi.spout.ISpoutDefinition;
import com.hmsonline.storm.osgi.topology.ITopologyDefinition;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rmoquin
 */
public class TopologyManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(TopologyManager.class);
  private IClojureLoader clojureLoader;

  public void onBindService(ITopologyDefinition definition) {
    try {
      this.submitTopology(definition);
    } catch (Exception ex) {
      LOGGER.error("Error binding topology service.", ex);
    }
  }

  public void onUnbindService(ITopologyDefinition definition) {
  }

  public void submitTopology(ITopologyDefinition definition) throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    List<ISpoutDefinition> spouts = definition.getSpouts();
    for (Iterator<ISpoutDefinition> it = spouts.iterator(); it.hasNext();) {
      ISpoutDefinition iSpoutDefinition = it.next();
      builder.setSpout(iSpoutDefinition.getName(), iSpoutDefinition, iSpoutDefinition.getParallelismHint());
    }
    List<IBoltDefinition> bolts = definition.getBolts();
    for (Iterator<IBoltDefinition> it = bolts.iterator(); it.hasNext();) {
      IBoltDefinition iBoltDefinition = it.next();
      builder.setBolt(iBoltDefinition.getName(), iBoltDefinition, iBoltDefinition.getParallelismHint()).shuffleGrouping("spout");
    }

    Config conf = new Config();
    conf.setDebug(true);
    /*    if (name != null) {
     conf.setNumWorkers(3);
     StormSubmitter.submitTopology(name, conf, builder.createTopology());
     } else {*/
    conf.setMaxTaskParallelism(3);
    LocalCluster cluster = (LocalCluster) this.clojureLoader.createInstance(this, "backtype.storm.LocalCluster");
    cluster.submitTopology("word-count", conf, builder.createTopology());
    cluster.shutdown();
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
