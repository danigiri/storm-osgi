package com.hmsonline.storm.osgi.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseComponent;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a configurable Storm BasicSpout.
 *
 * @org.apache.xbean.XBean element="basicSpout" description="Implements a simple configurable BasicSpout which listens
 * to tuples from it's tuple source."
 *
 * @author rmoquin
 */
public class BasicSpoutDefinition extends BaseComponent implements ISpoutDefinition {

  private static final Logger LOGGER = LoggerFactory.getLogger(BasicSpoutDefinition.class);
  private String name;
  protected String[] outputFields;
  private Integer parallelismHint;
  protected ITupleSource source;
  private SpoutOutputCollector collector;
  private Queue tupleQueue;

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields(outputFields));
  }

  @Override
  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    tupleQueue = new ConcurrentLinkedQueue();
    this.collector = collector;
    this.source.setTupleQueue(this.tupleQueue);
  }

  @Override
  public void close() {
    this.source.setTupleQueue(null);
    this.tupleQueue = null;
  }

  @Override
  public void activate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Spout is being activated.");
    }
  }

  @Override
  public void deactivate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Spout is being deactivated.");
    }
  }

  @Override
  public void nextTuple() {
    Object tuple = this.tupleQueue.poll();
    if (tuple == null) {
      return;
    }
    collector.emit(new Values(tuple));
  }

  @Override
  public void ack(Object msgId) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Tuple " + msgId + " was successfully processed.");
    }
  }

  @Override
  public void fail(Object msgId) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Tuple " + msgId + " was unsuccessfully processed.");
    }
  }

  /**
   * @return the outputFields
   */
  @Override
  public String[] getOutputFields() {
    return outputFields;
  }

  /**
   * @param outputFields the outputFields to set
   */
  @Override
  public void setOutputFields(String[] outputFields) {
    this.outputFields = outputFields;
  }

  /**
   * @return the source
   */
  @Override
  public ITupleSource getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  @Override
  public void setSource(ITupleSource source) {
    this.source = source;
  }

  /**
   * @return the parallelismHint
   */
  @Override
  public Integer getParallelismHint() {
    return parallelismHint;
  }

  /**
   * @param parallelismHint the parallelismHint to set
   */
  @Override
  public void setParallelismHint(Integer parallelismHint) {
    this.parallelismHint = parallelismHint;
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
}
