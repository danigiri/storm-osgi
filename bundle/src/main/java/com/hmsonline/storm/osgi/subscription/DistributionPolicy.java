package com.hmsonline.storm.osgi.subscription;

import backtype.storm.topology.BoltDeclarer;
import com.hmsonline.storm.osgi.topology.Subscription;

/**
 *
 * @author rmoquin
 */
public interface DistributionPolicy {
  public void setup(BoltDeclarer declarer, Subscription subscription);
}
