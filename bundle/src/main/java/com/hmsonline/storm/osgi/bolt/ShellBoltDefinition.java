package com.hmsonline.storm.osgi.bolt;

import backtype.storm.task.ShellBolt;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * Represents a configurable Storm ShellBolt.
 *
 * @org.apache.xbean.XBean element="shellBolt" description="Implements a simple configurable ShellBolt"
 *
 * @author rmoquin
 */
public class ShellBoltDefinition extends ShellBolt implements IBoltDefinition, IRichBolt {

  private String name;
  private Integer parallelismHint;
  private String[] command;
  private String[] outputFields;
  private Map<String, Object> configuration;

  public ShellBoltDefinition() {
    super("");
  }

  @PostConstruct
  public void init() {
    super.setCommandArray(command);
  }

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
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
   * @return the command
   */
  public String[] getCommand() {
    return command;
  }

  /**
   * @param command the command to set
   */
  public void setCommand(String[] command) {
    this.command = command;
  }

  /**
   * @return the outputFields
   */
  public String[] getOutputFields() {
    return outputFields;
  }

  /**
   * @param outputFields the outputFields to set
   */
  public void setOutputFields(String[] outputFields) {
    this.outputFields = outputFields;
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