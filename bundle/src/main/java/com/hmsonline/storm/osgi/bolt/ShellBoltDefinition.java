package com.hmsonline.storm.osgi.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.ShellBolt;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Tuple;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * A bolt that can be configured to run a shell command.
 *
 * @org.apache.xbean.XBean element="shellBolt" description="A bolt that can be configured to run a shell command."
 *
 * @author rmoquin
 */
public class ShellBoltDefinition extends BoltDefinition {

  private String[] command;
  private ShellBolt shellTask;

  @PostConstruct
  @Override
  public void initBolt() {
    shellTask = new ShellBolt(command);
    this.setExecutor(new IBoltExecutor() {

      @Override
      public List<Object> execute(Tuple tuple) {
        ShellBoltDefinition.this.shellTask.execute(tuple);
        return null;
      }
    });
    super.initBolt();
  }

  @Override
  public void prepare(Map configuration, TopologyContext context, OutputCollector collector) {
    super.prepare(configuration, context, collector);
    this.shellTask.prepare(configuration, context, collector);
  }

  @Override
  public void cleanup() {
    this.shellTask.cleanup();
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
}