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
 * @org.apache.xbean.XBean element="shellBolt" description="A bolt that can be
 * configured to run a shell command."
 *
 * @author rmoquin
 */
public class ShellBoltDefinition extends BoltDefinition {

  private String[] command;
  private ShellBolt shellTask;

  @PostConstruct
  @Override
  public void initBolt() {
    //Moved logic to prepare, since the annotations are apparently not called when clojure gets it's dirty little hands on things.
    //This also means that the super call needs commented in order to avoid failures when the annotation does get invoked.
    //super.initBolt();
  }

  @Override
  public void prepare(Map configuration, TopologyContext context, OutputCollector collector) {
    shellTask = new ShellBolt(command);
    this.setExecutor(new IBoltExecutor() {
      @Override
      public List<Object> execute(Tuple tuple) {
        ShellBoltDefinition.this.shellTask.execute(tuple);
        return null;
      }
    });
    super.prepare(configuration, context, collector);
    this.shellTask.prepare(configuration, context, collector);
  }

/*  @PreDestroy
  public void destroy() {
    this.shellTask.cleanup();
  }*/

  @Override
  public void cleanup() {
    //Why bother, this isn't guarenteed to be called anyhow....but I guess I have to since the PreDestroy
    //probably won't get called because Clojure will mess it up.
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