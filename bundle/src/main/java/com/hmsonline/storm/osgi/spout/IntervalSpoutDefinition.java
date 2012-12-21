package com.hmsonline.storm.osgi.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a Storm Spout which invokes it's ITupleSource at a specified interval to retrieve create tuples to be
 * emitted.
 *
 * @org.apache.xbean.XBean element="pollingSpout" description="Represents a Spout which invokes it's ITupleSource at a
 * specified interval to retrieve create tuples to be emitted."
 * @author rmoquin
 */
public class IntervalSpoutDefinition extends BasicSpoutDefinition {

  private ScheduledExecutorService executorService;
  private int interval;
  private TimeUnit unit;

  @Override
  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    if ((super.source == null) || !(this.source instanceof IPollingTupleSource)) {
      throw new IllegalStateException("The configured ITupleSource implementation must be an IPeriodicTupleSource subclass implementation.");
    }
    super.open(conf, context, collector);
    executorService = Executors.newScheduledThreadPool(1);
    executorService.schedule((IPollingTupleSource) super.source, interval, unit);
  }

  @Override
  public void close() {
    this.executorService.isShutdown();
    super.close();
  }

  /**
   * @return the interval
   */
  public int getInterval() {
    return interval;
  }

  /**
   * @param interval the interval to set
   */
  public void setInterval(int interval) {
    this.interval = interval;
  }

  /**
   * @return the unit
   */
  public TimeUnit getUnit() {
    return unit;
  }

  /**
   * @param unit the unit to set
   */
  public void setUnit(TimeUnit unit) {
    this.unit = unit;
  }
}
