package com.hmsonline.storm.osgi.topology;

import com.hmsonline.storm.osgi.subscription.DistributionPolicy;
import com.hmsonline.storm.osgi.subscription.ShuffleDistribution;
import com.hmsonline.storm.osgi.tuple.TupleStream;
import javax.annotation.PostConstruct;

/**
 * An owner POJO used for testing out nested properties
 *
 * @org.apache.xbean.XBean element="subscribes" description="Represents the
 * subscription to the emitted output of a component in a topology.
 *
 * @author rmoquin
 */
public class Subscription {

  private ITopologyComponent to;
  private TupleStream stream;
  private DistributionPolicy distribution;

  @PostConstruct
  public void init() {
    this.distribution = new ShuffleDistribution();
  }

  /**
   * @return the distribution
   */
  public DistributionPolicy getDistribution() {
    return distribution;
  }

  /**
   * @param distribution the distribution to set
   */
  public void setDistribution(DistributionPolicy distribution) {
    this.distribution = distribution;
  }

  /**
   * @return the to
   */
  public ITopologyComponent getTo() {
    return to;
  }

  /**
   * @param to the to to set
   */
  public void setTo(ITopologyComponent to) {
    this.to = to;
  }

  /**
   * @return the stream
   */
  public TupleStream getStream() {
    return stream;
  }

  /**
   * @param stream the stream to set
   */
  public void setStream(TupleStream stream) {
    this.stream = stream;
  }
}
