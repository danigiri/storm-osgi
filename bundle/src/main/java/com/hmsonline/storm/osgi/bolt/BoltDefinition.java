package com.hmsonline.storm.osgi.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.tuple.Tuple;
import com.hmsonline.storm.osgi.topology.ComponentDefinition;
import com.hmsonline.storm.osgi.topology.Subscription;
import com.hmsonline.storm.osgi.tuple.TupleStream;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The parent definition for bolts, it should be subclassed to make it easier to
 * assign a tag configuration for each implementation.
 * @org.apache.xbean.XBean
 * @author rmoquin
 */
public class BoltDefinition extends ComponentDefinition implements IRichBolt {

  private static final Logger LOGGER = LoggerFactory.getLogger(BoltDefinition.class);
  private Subscription subscription;
  private IBoltExecutor executor;
  private OutputCollector collector;
  protected TopologyContext context;

  @PostConstruct
  public void initBolt() {
    if (this.executor == null) {
      throw new IllegalStateException("This BoltDefinition requires a BoltExecutor to be specified.");
    }
  }

  @Override
  public void prepare(Map configuration, TopologyContext context, OutputCollector collector) {
    this.collector = collector;
    this.context = context;
    this.configuration = configuration;
  }

  @Override
  public void execute(Tuple tuple) {
    List<Object> output = this.executor.execute(tuple);
    if (this.getStreams() != null) {
      if (output != null) {
        for (TupleStream stream : super.getStreams()) {
          collector.emit(stream.getId(), output);
        }
      } else {
        LOGGER.error("An output stream/schema was defined for this bolt but it's executor returned null, no tuple will be sent!");
      }
    }
  }

  @Override
  public void cleanup() {
    //Why bother, this isn't guarenteed to be called anyhow....
  }

  /**
   * @return the executor
   */
  public IBoltExecutor getExecutor() {
    return executor;
  }

  /**
   * @param executor the executor to set
   */
  public void setExecutor(IBoltExecutor executor) {
    this.executor = executor;
  }

  /**
   * The details of what this bolt is subscribed to.
   *
   * @return the subscription
   */
  public Subscription getSubscription() {
    return subscription;
  }

  /**
   * Sets the details of what this bolt is subscribed to.
   *
   * @param subscription the subscription to set
   */
  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }
}
