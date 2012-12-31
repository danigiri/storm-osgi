package com.hmsonline.storm.osgi.topology;

import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseComponent;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import com.hmsonline.storm.osgi.tuple.TupleStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @org.apache.xbean.XBean
 * @author rmoquin
 */
public class ComponentDefinition extends BaseComponent implements ITopologyComponent {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComponentDefinition.class);
  protected String name;
  private Integer parallelismHint;
  private String[] schema;
  private TupleStream[] streams;
  protected Map<String, Object> configuration;

  @PostConstruct
  public void initDefinition() {
    if ((streams == null) && (schema != null)) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Component, " + this.name + ", has a default tuple schema, creating a default stream for it.");
      }
      TupleStream defaultStream = new TupleStream();
      defaultStream.setId(Utils.DEFAULT_STREAM_ID);
      defaultStream.setSchema(schema);
      streams = new TupleStream[]{defaultStream};
    }
  }
  
  @PreDestroy
  public void destroyDefinition() {
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    for (TupleStream stream : streams) {
      declarer.declareStream(stream.getId(), new Fields(stream.getSchema()));
    }
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

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return this.configuration;
  }

  public void setComponentConfiguration(Map<String, Object> configuration) {
    this.configuration = configuration;
  }

  /**
   * The schema for this component.
   *
   * @return the schema
   */
  public String[] getSchema() {
    return schema;
  }

  /**
   * Sets the schema for this component.
   *
   * @param schema the schema to set
   */
  public void setSchema(String[] schema) {
    this.schema = schema;
  }

  /**
   * @return the streams
   */
  public TupleStream[] getStreams() {
    return streams;
  }
}
