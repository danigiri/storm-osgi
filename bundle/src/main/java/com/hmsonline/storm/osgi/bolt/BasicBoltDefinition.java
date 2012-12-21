package com.hmsonline.storm.osgi.bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseComponent;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * Represents a configurable Storm BasicBolt.
 *
 * @org.apache.xbean.XBean element="basicBolt" description="Implements a simple configurable BasicBolt"
 *
 * @author rmoquin
 */
public class BasicBoltDefinition extends BaseComponent implements IBoltDefinition {
  private String name;
  private Integer parallelismHint;
  private IBoltExecutor executor;
  private String[] outputFields;
  private Map<String, Object> configuration;

  @PostConstruct
  public void init() {
    if (this.executor == null) {
      throw new IllegalStateException("This BoltDefinition requires a non-null BoltExecutor to be specified.");
    }
  }
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    Values v = this.executor.execute(tuple);
    collector.emit(v);
  }
  
@Override
  public void cleanup() {
    //Why bother, this isn't guarenteed to be called anyhow....
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields(outputFields));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return this.configuration;
  }

  public void setComponentConfiguration(Map<String, Object> configuration) {
    this.configuration = configuration;
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
}
