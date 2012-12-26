package com.hmsonline.storm.osgi.tuple;

/**
 *
 * @author rmoquin
 */
public interface ITupleStream {

  public String getId();

  public void setId(String id);

  public ITupleSchema getSchema();

  public void setSchema(ITupleSchema schema);
}
