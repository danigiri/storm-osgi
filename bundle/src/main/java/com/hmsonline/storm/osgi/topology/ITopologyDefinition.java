package com.hmsonline.storm.osgi.topology;

import backtype.storm.Config;
import com.hmsonline.storm.osgi.bolt.IBoltDefinition;
import com.hmsonline.storm.osgi.spout.ISpoutDefinition;
import java.util.List;

/**
 *
 * @author rmoquin
 */
public interface ITopologyDefinition {

  /**
   * @return the bolts
   */
  List<IBoltDefinition> getBolts();

  /**
   * @return the spouts
   */
  List<ISpoutDefinition> getSpouts();

  /**
   * @param bolts the bolts to set
   */
  void setBolts(List<IBoltDefinition> bolts);

  /**
   * @param spouts the spouts to set
   */
  void setSpouts(List<ISpoutDefinition> spouts);

  /**
   * @return the config
   */
  Config getConfig();

  /**
   * @return the name
   */
  String getName();

  /**
   * @param config the config to set
   */
  void setConfig(Config config);

  /**
   * @param name the name to set
   */
  void setName(String name);
  
}
