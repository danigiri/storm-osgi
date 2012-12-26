package com.hmsonline.storm.osgi.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import com.hmsonline.storm.osgi.topology.ComponentDefinition;
import com.hmsonline.storm.osgi.tuple.TupleStream;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rmoquin
 */
public abstract class SpoutDefinition<T extends ITupleSource> extends ComponentDefinition implements IRichSpout {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpoutDefinition.class);
  protected T source;
  private SpoutOutputCollector collector;
  private Queue<List> tupleQueue;

  @Override
  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    this.source = this.getSource();
    if (this.source == null) {
      throw new IllegalStateException("A tuple source implementation must be configured.");
    }
    tupleQueue = new ConcurrentLinkedQueue<List>();
    this.collector = collector;
    this.source.setTupleQueue(this.tupleQueue);
  }

  @Override
  public void close() {
    this.source.setTupleQueue(null);
    this.tupleQueue.clear();
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
    List<Object> tuple = this.tupleQueue.poll();
    if (tuple == null) {
      return;
    }
    for (TupleStream stream : super.streams) {
      collector.emit(stream.getId(), tuple);
    }
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

  public abstract T getSource();

  public abstract void setSource(T source);
}
